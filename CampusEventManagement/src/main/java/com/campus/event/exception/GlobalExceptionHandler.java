package com.campus.event.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handle(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "login";
    }
}