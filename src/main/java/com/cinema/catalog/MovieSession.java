package com.cinema.catalog;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Entity
@Table(name = "movie_sessions")
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;


    @Column(name = "start_time", nullable = false)
    private OffsetDateTime startTime;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected MovieSession() {
    }

    public MovieSession(Movie movie, Hall hall, OffsetDateTime
            startTime, BigDecimal price) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.price = price;
    }

    public Long getId() { return id; }
    public Movie getMovie() { return movie; }
    public Hall getHall() { return hall; }
    public OffsetDateTime getStartTime() { return startTime; }
    public BigDecimal getPrice() { return price; }
    public OffsetDateTime getCreatedAt() { return createdAt; }

    public void setMovie(Movie movie) { this.movie = movie; }
    public void setHall(Hall hall) { this.hall = hall; }
    public void setStartTime(OffsetDateTime startTime) { this.
            startTime = startTime; }
    public void setPrice(BigDecimal price) { this.price = price; }
}