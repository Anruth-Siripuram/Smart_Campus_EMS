package com.campus.event.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    // 🔥 ENUM (USED PROPERLY NOW)
    public enum Status {
        BOOKED,
        CANCELLED,
        PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    private int ticketsCount;

    private double totalPrice;

    // 🔥 FIXED FIELD (ENUM + STRING STORAGE)
    @Enumerated(EnumType.STRING)
    private Status status;

    private String ticketId;

    @PrePersist
    public void generateId() {
        this.ticketId = UUID.randomUUID().toString();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getTicketsCount() {
		return ticketsCount;
	}

	public void setTicketsCount(int ticketsCount) {
		this.ticketsCount = ticketsCount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
}