package com.cinema.booking;

import com.cinema.catalog.MovieSession;
import com.cinema.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tickets",
        uniqueConstraints = @UniqueConstraint(columnNames = {"session_id", "row_number", "seat_number"}))
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

    @Column(nullable = false)
    private String status;

    @Column(name = "hold_expires_at")
    private OffsetDateTime holdExpiresAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected Ticket(){}

    private Ticket(MovieSession session, User user, Integer rowNumber, Integer seatNumber, String status){
        this.session = session;
        this.user = user;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public OffsetDateTime getHoldExpiresAt() {
        return holdExpiresAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHoldExpiresAt(OffsetDateTime holdExpiresAt) {
        this.holdExpiresAt = holdExpiresAt;
    }
}
