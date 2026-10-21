package com.cinema.booking;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(long ticketId) {
        super("Билет " + ticketId + " не найден");
    }
}
