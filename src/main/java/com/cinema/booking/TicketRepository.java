package com.cinema.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatusAndHoldExpiresAtBefore(TicketStatus status, OffsetDateTime before);
}
