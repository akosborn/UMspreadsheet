package com.umspreadsheet.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionControllerAdvice
{
    @ExceptionHandler(Exception.class)
    public String exception(Exception exception, Model model)
    {
        model.addAttribute("exception", exception);

        exception.printStackTrace();

        return "globalError";
    }
}
