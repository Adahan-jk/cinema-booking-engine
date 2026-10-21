package com.cinema.booking;

import com.cinema.catalog.MovieSession;
import com.cinema.user.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private MovieSession session;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "row_number", nullable = false)
    private Integer rowNumber;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Column(name = "hold_expires_at")
    private OffsetDateTime holdExpiresAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected Ticket() {
    }

    private Ticket(MovieSession session, User user, Integer rowNumber, Integer seatNumber,
                   TicketStatus status, OffsetDateTime holdExpiresAt) {
        this.session = session;
        this.user = user;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.status = status;
        this.holdExpiresAt = holdExpiresAt;
        this.createdAt = OffsetDateTime.now();
    }

    public static Ticket hold(MovieSession session, User user, Integer rowNumber, Integer seatNumber,
                              OffsetDateTime holdExpiresAt) {
        return new Ticket(session, user, rowNumber, seatNumber, TicketStatus.HOLD, holdExpiresAt);
    }

    public long getId() {
        return id;
    }

    public MovieSession getSession() {
        return session;
    }

    public User getUser() {
        return user;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public OffsetDateTime getHoldExpiresAt() {
        return holdExpiresAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void pay() {
        this.status = TicketStatus.PAID;
        this.holdExpiresAt = null;
    }

    public void expire() {
        this.status = TicketStatus.EXPIRED;
    }
}
