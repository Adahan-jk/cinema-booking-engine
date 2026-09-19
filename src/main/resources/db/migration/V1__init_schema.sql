CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash TEXT NOT NULL,
                       role VARCHAR(20) NOT NULL DEFAULT 'USER',
                       created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE movies
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    description      TEXT,
    duration_minutes INT          NOT NULL CHECK (duration_minutes > 0),
    rating           NUMERIC(3, 1),
    created_at       TIMESTAMPTZ  NOT NULL DEFAULT now()
);


CREATE TABLE halls (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL UNIQUE,
                       total_rows INT NOT NULL,
                       seats_per_row INT NOT NULL
);

CREATE TABLE movie_sessions (
                                id BIGSERIAL PRIMARY KEY,
                                movie_id BIGINT NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
                                hall_id BIGINT NOT NULL REFERENCES halls(id),
                                start_time TIMESTAMPTZ NOT NULL,
                                price NUMERIC(10,2) NOT NULL,
                                created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);


CREATE TABLE tickets (
                         id BIGSERIAL PRIMARY KEY,
                         session_id BIGINT NOT NULL REFERENCES movie_sessions(id) ON DELETE CASCADE,
                         user_id BIGINT NOT NULL REFERENCES users(id),
                         row_number INT NOT NULL,
                         seat_number INT NOT NULL,
                         status VARCHAR(20) NOT NULL DEFAULT 'HOLD',
                         hold_expires_at TIMESTAMPTZ,
                         created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                         UNIQUE (session_id, row_number, seat_number)
);

CREATE INDEX idx_sessions_movie_start ON movie_sessions(movie_id, start_time);
CREATE INDEX idx_tickets_session ON tickets(session_id);
CREATE INDEX idx_tickets_user ON tickets(user_id);
