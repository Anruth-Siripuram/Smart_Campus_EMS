package com.campus.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campus.event.model.Event;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByType(String type);
}