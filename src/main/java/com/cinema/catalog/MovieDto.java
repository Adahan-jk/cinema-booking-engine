package com.cinema.catalog;

import java.math.BigDecimal;

public class MovieDto {
    private final Long id;
    private final String title;
    private final String description;
    private final Integer durationMinutes;
    private final BigDecimal rating;

    public MovieDto(Long id, String title, String description, Integer durationMinutes, BigDecimal rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public BigDecimal getRating() {
        return rating;
    }
}
