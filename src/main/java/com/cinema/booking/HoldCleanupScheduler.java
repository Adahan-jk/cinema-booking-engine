package com.cinema.booking;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class HoldCleanupScheduler {

    private final TicketRepository tickets;

    public HoldCleanupScheduler(TicketRepository tickets) {
        this.tickets = tickets;
    }

    @Scheduled(fixedDelayString = "${app.booking.cleanup-interval:PT1M}")
    @Transactional
    public void expireStaleHolds() {
        List<Ticket> stale = tickets.findByStatusAndHoldExpiresAtBefore(TicketStatus.HOLD, OffsetDateTime.now());
        for (Ticket ticket : stale) {
            ticket.expire();
        }
    }
}
