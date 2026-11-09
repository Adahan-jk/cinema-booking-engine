package com.cinema.payment;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PaymentResult {
    private final boolean success;
    private final String transactionId;
    private final BigDecimal amount;
    private final OffsetDateTime paidAt;

    public PaymentResult(boolean success, String transactionId, BigDecimal amount, OffsetDateTime paidAt){
        this.success = success;
        this.transactionId = transactionId;
        this.amount = amount;
        this.paidAt = paidAt;
    }

    public boolean isSuccess(){return success;}
    public String getTransactionId(){return transactionId;}

    public BigDecimal getAmount() {
        return amount;
    }

    public OffsetDateTime getPaidAt() {
        return paidAt;
    }
}
