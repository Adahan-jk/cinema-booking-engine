package com.cinema.payment;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected Payment(){
    }

    public static Payment success(long ticketId, BigDecimal amount, String transactionId){
        return new Payment(ticketId, amount, PaymentStatus.SUCCESS, transactionId, OffsetDateTime.now());
    }

    public Payment(Long ticketId, BigDecimal amount, PaymentStatus status, String transactionId, OffsetDateTime createdAt) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
