package com.cinema.booking;

public record TicketConfirmedEvent(long ticketId, long userId, String movieTitle) {

}
