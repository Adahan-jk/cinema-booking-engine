package com.cinema.booking;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


public class BookingResponse {
    private final long ticketId;
    private final Long sessionId;
    private final String movieTitle;
    private final String hallName;
    private final Integer rowNumber;
    private final Integer seatNumber;
    private final BigDecimal price;
    private final TicketStatus status;
    private final OffsetDateTime holdExpiresAt;

    public BookingResponse(long ticketId, Long sessionId, String movieTitle, String hallName,
                           Integer rowNumber, Integer seatNumber, BigDecimal price, TicketStatus status, OffsetDateTime holdExpiresAt){
        this.ticketId = ticketId;
        this.sessionId = sessionId;
        this.movieTitle = movieTitle;
        this.hallName = hallName;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
        this.holdExpiresAt = holdExpiresAt;
    }

    public long getTicketId() {
        return ticketId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getHallName() {
        return hallName;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public OffsetDateTime getHoldExpiresAt() {
        return holdExpiresAt;
    }
}
