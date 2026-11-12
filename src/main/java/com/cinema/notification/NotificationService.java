package com.cinema.notification;

public interface NotificationService {
    void bookingConfirmed(long userId, long ticketId, String movieTitle);
}
