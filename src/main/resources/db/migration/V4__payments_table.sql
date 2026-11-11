CREATE TABLE payments (
       id BIGSERIAL PRIMARY KEY,
       ticket_id BIGINT NOT NULL REFERENCES tickets(id),
       amount  NUMERIC(10, 2) NOT NULL CHECK (amount > 0),
       status VARCHAR(20) NOT NULL DEFAULT 'SUCCESS',
       transaction_id VARCHAR(100) NOT NULL UNIQUE,
       created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_payments_ticket ON payments(ticket_id);
