ALTER TABLE tickets DROP CONSTRAINT tickets_session_id_row_number_seat_number_key;

CREATE UNIQUE INDEX idx_tickets_active_seat
    ON tickets (session_id, row_number, seat_number)
    WHERE status IN ('HOLD', 'PAID');

INSERT INTO users (username, email, password_hash, role)
VALUES ('demo', 'demo@cinema.local', 'seed-not-used', 'USER');
