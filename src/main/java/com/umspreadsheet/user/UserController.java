package com.umspreadsheet.user;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.ObjectUtils;

import javax.inject.Singleton;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Map;


@Controller
public class UserController
{
    private SimpleUserService userService;

    @Value("${CLOUDINARY_URL}")
    String cloudinaryCredentials;

    @Autowired
    public UserController(SimpleUserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping("/user/{username}")
    public String getProfile(@PathVariable String username, Model model, Principal principal)
    {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);

        // Check if this request was a redirect after a validation error from updateProfile()
        if (!model.containsAttribute("userForm"))
        {
            model.addAttribute("userForm", new User());
        }
        // Set a flag to notify the page that errors were present
        else
        {
            model.addAttribute("formErrors", true);
        }

        return "user/profile";
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
    public String updateProfile(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes)
    {
        String username = user.getUsername();

        if (bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm", bindingResult);
            redirectAttributes.addFlashAttribute("userForm", user);

            return "redirect:/user/" + username;
        }

        User retrievedUser = userService.findByUsername(username);

        retrievedUser.setTwitterHandle(user.getTwitterHandle());
        retrievedUser.setLocation(user.getLocation());
        retrievedUser.setFavoriteSongs(user.getFavoriteSongs());

        User savedUser = userService.save(retrievedUser);

        return "redirect:/user/" + username;
    }

    @RequestMapping(value = "/user/{username}/avatar", method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("file")MultipartFile multipartFile,
                               @PathVariable String username,
                               RedirectAttributes redirectAttributes)
    {
        Cloudinary cloudinary = new Cloudinary(cloudinaryCredentials);

        User retrievedUser = userService.findByUsername(username);

        try
        {
            Map uploadResult = cloudinary.uploader().upload(convertToFile(multipartFile), com.cloudinary.utils.ObjectUtils.asMap(
                    "transformation", new Transformation().crop("limit").width(130).height(130)
            ));

            // Delete old avatar if there is one
            if (retrievedUser.getAvatarUrl() != null)
                cloudinary.uploader().destroy(retrievedUser.getAvatarUrl(), com.cloudinary.utils.ObjectUtils.emptyMap());

            retrievedUser.setAvatarUrl(uploadResult.get("public_id").toString());

            User savedUser = userService.save(retrievedUser);
        } catch (IOException e)
        {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("uploadFailed", "Avatar upload failed.");
        }

        return "redirect:/user/" + username;
    }

    private File convertToFile(MultipartFile file) throws IOException
    {
        String[] tokens = file.getOriginalFilename().split("\\.(?=[^\\.]+$)");
        File convFile = File.createTempFile("temp", "." + tokens[1]);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }
}
