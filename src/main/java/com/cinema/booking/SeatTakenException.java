package com.cinema.booking;

public class SeatTakenException extends RuntimeException {
    public SeatTakenException(Long sessionId, Integer rowNumber, Integer seatNumber) {
        super("Место уже занято: сеанс " + sessionId + ", ряд " + rowNumber + ", место " + seatNumber);
    }
}
