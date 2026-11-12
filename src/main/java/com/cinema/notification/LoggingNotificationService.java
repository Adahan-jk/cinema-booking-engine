package com.cinema.notification;

import org.springframework.stereotype.Service;

@Service
public class LoggingNotificationService implements NotificationService{
    @Override
    public void bookingConfirmed(long userId, long ticketId, String movieTitle){
        System.out.println("УВЕДОМЛЕНИЕ: пользователь " + userId + ", ваш билет " + ticketId + " на \"" + movieTitle + "\" оформлен!");
    }
}
