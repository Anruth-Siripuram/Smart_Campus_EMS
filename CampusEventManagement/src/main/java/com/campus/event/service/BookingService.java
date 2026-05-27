package com.campus.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.event.model.*;
import com.campus.event.repository.*;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    @Autowired
    private EventRepository eventRepo;

    public Booking book(User user, Long eventId, int count) {

        Event e = eventRepo.findById(eventId).orElseThrow();

        if (e.getAvailableTickets() < count)
            throw new RuntimeException("Not enough tickets");

        e.setAvailableTickets(e.getAvailableTickets() - count);
        eventRepo.save(e);

        Booking b = new Booking();
        b.setUser(user);
        b.setEvent(e);
        b.setTicketsCount(count);
        b.setTotalPrice(count * e.getPrice());
        b.setStatus(Booking.Status.BOOKED);

        return repo.save(b);
    }

    public List<Booking> getUserBookings(User user) {
        return repo.findByUser(user);
    }

    public List<Booking> getAll() {
        return repo.findAll();
    }

    public void requestCancel(Long id) {
        Booking b = repo.findById(id).orElseThrow();
        b.setStatus(Booking.Status.PENDING);
        repo.save(b);
    }

    public void approveCancel(Long id) {
        Booking b = repo.findById(id).orElseThrow();
        b.setStatus(Booking.Status.CANCELLED);

        Event e = b.getEvent();
        e.setAvailableTickets(e.getAvailableTickets() + b.getTicketsCount());

        eventRepo.save(e);
        repo.save(b);
    }

    public Booking getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    // 🔥 USERS LIST
    public List<User> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(Booking::getUser)
                .distinct()
                .toList();
    }

    // 🔥 USER BOOKINGS
    public List<Booking> getBookingsByUserId(Long userId) {
        return repo.findAll()
                .stream()
                .filter(b -> b.getUser().getId().equals(userId))
                .toList();
    }
}