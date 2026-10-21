package com.cinema.booking;

public class HoldExpiredException extends Exception {
    public HoldExpiredException(long ticketId) {
        super("Время удержания билета " + ticketId + " истекло");
    }
}
