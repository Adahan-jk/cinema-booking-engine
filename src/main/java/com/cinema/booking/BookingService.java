package com.cinema.booking;

import com.cinema.catalog.MovieSession;
import com.cinema.catalog.MovieSessionRepository;
import com.cinema.user.CurrentUserProvider;
import com.cinema.user.User;
import com.cinema.user.UserNotFoundException;
import com.cinema.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;

@Service
public class BookingService {

    private final TicketRepository tickets;
    private final MovieSessionRepository sessions;
    private final UserRepository users;
    private final CurrentUserProvider currentUser;
    private final StringRedisTemplate redis;
    private final Duration holdTtl;

    public BookingService(TicketRepository tickets,
                          MovieSessionRepository sessions,
                          UserRepository users,
                          CurrentUserProvider currentUser,
                          StringRedisTemplate redis,
                          @Value("${app.booking.hold-ttl:PT10M}") Duration holdTtl) {
        this.tickets = tickets;
        this.sessions = sessions;
        this.users = users;
        this.currentUser = currentUser;
        this.redis = redis;
        this.holdTtl = holdTtl;
    }

    @Transactional
    public BookingResponse hold(BookingRequest request) {
        String key = holdKey(request.getSessionId(), request.getRowNumber(), request.getSeatNumber());
        Boolean acquired = redis.opsForValue().setIfAbsent(key, "0", holdTtl);
        if (!Boolean.TRUE.equals(acquired)) {
            throw new SeatTakenException(request.getSessionId(), request.getRowNumber(), request.getSeatNumber());
        }
        try {
            MovieSession session = sessions.findById(request.getSessionId())
                    .orElseThrow(() -> new SessionNotFoundException(request.getSessionId()));
            User user = users.findById(currentUser.currentUserId())
                    .orElseThrow(() -> new UserNotFoundException(currentUser.currentUserId()));
            OffsetDateTime expiresAt = OffsetDateTime.now().plus(holdTtl);
            Ticket ticket = Ticket.hold(session, user, request.getRowNumber(), request.getSeatNumber(), expiresAt);
            tickets.saveAndFlush(ticket);
            redis.opsForValue().set(key, String.valueOf(ticket.getId()), holdTtl);
            return toResponse(ticket);
        } catch (DataIntegrityViolationException e) {
            redis.delete(key);
            throw new SeatTakenException(request.getSessionId(), request.getRowNumber(), request.getSeatNumber());
        } catch (RuntimeException e) {
            redis.delete(key);
            throw e;
        }
    }

    @Transactional
    public BookingResponse confirm(long ticketId) throws HoldExpiredException {
        Ticket ticket = tickets.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));
        if (ticket.getStatus() == TicketStatus.PAID) {
            throw new TicketNotConfirmableException(ticketId, ticket.getStatus());
        }
        if (ticket.getStatus() == TicketStatus.EXPIRED) {
            throw new HoldExpiredException(ticketId);
        }
        if (ticket.getHoldExpiresAt() != null && ticket.getHoldExpiresAt().isBefore(OffsetDateTime.now())) {
            ticket.expire();
            tickets.saveAndFlush(ticket);
            redis.delete(holdKey(ticket));
            throw new HoldExpiredException(ticketId);
        }
        ticket.pay();
        tickets.saveAndFlush(ticket);
        redis.delete(holdKey(ticket));
        return toResponse(ticket);
    }

    private BookingResponse toResponse(Ticket ticket) {
        MovieSession session = ticket.getSession();
        return new BookingResponse(
                ticket.getId(),
                session.getId(),
                session.getMovie().getTitle(),
                session.getHall().getName(),
                ticket.getRowNumber(),
                ticket.getSeatNumber(),
                session.getPrice(),
                ticket.getStatus(),
                ticket.getHoldExpiresAt());
    }

    private String holdKey(Long sessionId, Integer rowNumber, Integer seatNumber) {
        return "hold:" + sessionId + ":" + rowNumber + ":" + seatNumber;
    }

    private String holdKey(Ticket ticket) {
        return holdKey(ticket.getSession().getId(), ticket.getRowNumber(), ticket.getSeatNumber());
    }
}
