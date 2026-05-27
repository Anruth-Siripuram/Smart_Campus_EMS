package com.campus.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campus.event.model.*;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // 👤 user bookings
    List<Booking> findByUser(User user);

    // 🔥 active booking count (excluding cancelled)
    long countByEventIdAndStatusNot(Long eventId, Booking.Status status);

    // 🔥 check if active bookings exist
    boolean existsByEventIdAndStatusNot(Long eventId, Booking.Status status);

    // 🔥 VERY IMPORTANT (for delete fix)
    void deleteByEventId(Long eventId);
}