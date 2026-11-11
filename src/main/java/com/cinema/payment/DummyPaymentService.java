package com.cinema.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class DummyPaymentService implements PaymentService{
    private final boolean forceFail;

    public DummyPaymentService(@Value("${app.payment.force-fail:false}") boolean forceFail){
        this.forceFail = forceFail;
    }

    @Override
    public PaymentResult pay(long ticketId, BigDecimal amount) {
        if (forceFail) {
            throw new PaymentFailedException(ticketId, "force-fail включен в конфигурации");
        }
        String transactionId = "DUMMY-" + UUID.randomUUID();
        return new PaymentResult(true, transactionId, amount, OffsetDateTime.now());
    }
}
