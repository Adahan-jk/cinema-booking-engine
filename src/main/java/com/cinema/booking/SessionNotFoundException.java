package com.cinema.booking;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(Long sessionId) {
        super("Сеанс " + sessionId + " не найден");
    }
}
