package com.cinema.booking;

public class TicketNotConfirmableException extends RuntimeException {
    public TicketNotConfirmableException(long ticketId, TicketStatus status) {
        super("Билет " + ticketId + " нельзя подтвердить: текущий статус " + status);
    }
}
