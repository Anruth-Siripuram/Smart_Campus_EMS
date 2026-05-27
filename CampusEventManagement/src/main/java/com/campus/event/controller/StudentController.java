package com.campus.event.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.campus.event.model.User;
import com.campus.event.service.*;

@Controller
public class StudentController {

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "home";
    }

    @GetMapping("/event/{id}")
    public String event(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.get(id));
        return "event-details";
    }
}