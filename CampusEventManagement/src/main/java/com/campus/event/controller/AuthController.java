package com.campus.event.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.campus.event.model.User;
import com.campus.event.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        service.register(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session, Model model) {

        User user = service.login(email, password);

        if (user != null) {
            session.setAttribute("user", user);

            if (user.getRole() == User.Role.ADMIN)
                return "redirect:/admin/dashboard";

            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
}