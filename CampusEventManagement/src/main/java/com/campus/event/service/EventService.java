package com.campus.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.campus.event.model.Event;
import com.campus.event.model.Booking;
import com.campus.event.repository.EventRepository;
import com.campus.event.repository.BookingRepository;

import java.util.List;

@Service
@Transactional   // 🔥 VERY IMPORTANT (applies to all methods)
public class EventService {

    @Autowired
    private EventRepository repo;

    @Autowired
    private BookingRepository bookingRepo;

    // 💾 SAVE EVENT
    public Event save(Event e) {

        if (e.getAvailableTickets() == 0) {
            e.setAvailableTickets(e.getTotalTickets());
        }

        return repo.save(e);
    }

    // 📄 GET ALL EVENTS
    public List<Event> getAll() {
        return repo.findAll();
    }

    // 🔍 GET EVENT BY ID
    public Event get(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ❌ SAFE DELETE (FINAL WORKING)
    public void delete(Long id) {

        // 🔥 check ACTIVE bookings
        boolean hasActiveBookings =
                bookingRepo.existsByEventIdAndStatusNot(
                        id,
                        Booking.Status.CANCELLED
                );

        if (hasActiveBookings) {
            throw new RuntimeException("Cannot delete event: active bookings exist.");
        }

        // 🔥 delete child rows first
        bookingRepo.deleteByEventId(id);

        // 🔥 delete parent row
        repo.deleteById(id);
    }

    // 🔥 ACTIVE BOOKING COUNT
    public long getActiveBookingCount(Long eventId) {

        return bookingRepo.countByEventIdAndStatusNot(
                eventId,
                Booking.Status.CANCELLED
        );
    }
}