package com.cinema.catalog;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class MovieSessionDto {
    private final Long id;
    private final Long movieId;
    private final String movieTitle;
    private final Long hallId;
    private final String hallName;
    private final OffsetDateTime startTime;
    private final BigDecimal price;

    public MovieSessionDto(Long id, Long movieId, String movieTitle, Long hallId, String hallName,
                           OffsetDateTime startTime, BigDecimal price) {
        this.id = id;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.hallId = hallId;
        this.hallName = hallName;
        this.startTime = startTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Long getHallId() {
        return hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
