package com.cinema.payment;

public class PaymentFailedException extends RuntimeException{
    public PaymentFailedException(long ticketId, String reason){
        super("Оплата билета " + ticketId + " не прошла: " + reason);
    }
}
