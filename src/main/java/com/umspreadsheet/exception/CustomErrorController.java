package com.umspreadsheet.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController
{
    private static final String ERROR_PATH = "/error";
    private static final String ERROR_TEMPLATE = "error";

    private final ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes)
    {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(ERROR_PATH)
    public String error(Model model, WebRequest request)
    {
        Map<String, Object> error = getErrorAttributes(request);

        model.addAttribute("timestamp", error.get("timestamp"));
        model.addAttribute("status", error.get("status"));
        model.addAttribute("error", error.get("error"));
        model.addAttribute("message", error.get("message"));
        //model.addAttribute("path", error.get("path"));

        // logging

        return ERROR_TEMPLATE;
    }

    @Override
    public String getErrorPath()
    {
        return ERROR_PATH;
    }

    @RequestMapping("/404")
    public String pageNotFound(Model model, WebRequest webRequest)
    {
        model.addAttribute("error", getErrorAttributes(webRequest));

        return "404";
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest)
    {
        return this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
    }
}
