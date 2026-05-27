package com.campus.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.campus.event.model.Event;
import com.campus.event.service.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    // 🧑‍💼 DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        var events = eventService.getAll();

        Map<Long, Long> activeCounts = new HashMap<>();

        for (Event e : events) {
            activeCounts.put(e.getId(), eventService.getActiveBookingCount(e.getId()));
        }

        model.addAttribute("events", events);
        model.addAttribute("activeCounts", activeCounts);

        return "admin-dashboard";
    }

    // ➕ ADD EVENT
    @GetMapping("/add")
    public String addPage() {
        return "add-event";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Event e) {
        eventService.save(e);
        return "redirect:/admin/dashboard";
    }

    // ❌ DELETE
    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {

        try {
            eventService.delete(id);
            return "redirect:/admin/dashboard";

        } catch (RuntimeException e) {

            var events = eventService.getAll();

            Map<Long, Long> activeCounts = new HashMap<>();
            for (Event ev : events) {
                activeCounts.put(ev.getId(), eventService.getActiveBookingCount(ev.getId()));
            }

            model.addAttribute("events", events);
            model.addAttribute("activeCounts", activeCounts);
            model.addAttribute("error", e.getMessage());

            return "admin-dashboard";
        }
    }

    // ✅ APPROVE CANCEL
    @PostMapping("/approve")
    public String approve(@RequestParam Long id) {
        bookingService.approveCancel(id);
        return "redirect:/admin/cancellations";
    }

    // ✏️ EDIT
    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.get(id));
        return "edit-event";
    }

    // 📋 CANCELLATIONS
    @GetMapping("/cancellations")
    public String cancellations(Model model) {
        model.addAttribute("bookings", bookingService.getAll());
        return "cancellation-requests";
    }

    // 📊 STATS (MAIN)
    @GetMapping("/stats")
    public String stats(Model model) {

        var bookings = bookingService.getAll();
        var users = bookingService.getAllUsers();

        model.addAttribute("events", eventService.getAll());
        model.addAttribute("bookings", bookings);
        model.addAttribute("users", users);

        double totalRevenue = bookings.stream()
                .mapToDouble(b -> b.getTotalPrice())
                .sum();

        model.addAttribute("totalRevenue", totalRevenue);

        return "stats";
    }

    // 👤 USER STATS PAGE
    @GetMapping("/stats/user/{id}")
    public String userStats(@PathVariable Long id, Model model) {

        var userBookings = bookingService.getBookingsByUserId(id);

        double totalRevenue = userBookings.stream()
                .mapToDouble(b -> b.getTotalPrice())
                .sum();

        model.addAttribute("bookings", userBookings);
        model.addAttribute("totalRevenue", totalRevenue);

        return "user-stats";
    }
}