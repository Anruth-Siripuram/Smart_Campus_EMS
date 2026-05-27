package com.campus.event.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.campus.event.model.Booking;
import com.campus.event.model.User;
import com.campus.event.service.BookingService;
import com.campus.event.util.QRGenerator;
import com.campus.event.util.PDFGenerator;
@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/book")
    public String book(@RequestParam Long eventId,
                       @RequestParam int tickets,
                       HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        bookingService.book(user, eventId, tickets);
        return "redirect:/my-bookings";
    }
    @GetMapping("/my-bookings")
    public String myBookings(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("bookings", bookingService.getUserBookings(user));
        return "my-bookings";
    }
    @PostMapping("/cancel")
    public String requestCancel(@RequestParam Long id) {
        bookingService.requestCancel(id);
        return "redirect:/my-bookings";
    }
    @GetMapping("/ticket/{id}")
    public String viewTicket(@PathVariable Long id, Model model) throws Exception {
        Booking booking = bookingService.getById(id);
        String qr = QRGenerator.generateQR(booking.getTicketId());
        model.addAttribute("booking", booking);
        model.addAttribute("qr", qr);
        return "ticket";
    }
    @GetMapping("/ticket/pdf/{id}")
    public ResponseEntity<byte[]> downloadTicket(@PathVariable Long id) throws Exception {
        Booking b = bookingService.getById(id);
        byte[] pdf = PDFGenerator.generateTicket(b);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=ticket.pdf")
                .body(pdf);
    }
}