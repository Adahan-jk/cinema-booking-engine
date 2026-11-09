package com.cinema.payment;

import java.math.BigDecimal;

public interface PaymentService {
    PaymentResult pay(long ticketId, BigDecimal amount);
}
