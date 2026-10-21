INSERT INTO movies (title, description, duration_minutes, rating)
VALUES ('Дюна: Часть вторая', 'Продолжение эпической саги', 166, 8.5),
       ('Интерстеллар', 'Спасение человечества через червоточины', 169, 8.7);

INSERT INTO halls (name, total_rows, seats_per_row)
VALUES ('Синий зал', 5, 8),
       ('Зал IMAX', 3, 10);

INSERT INTO movie_sessions (movie_id, hall_id, start_time, price)
VALUES (1, 1, now() + interval '1 day', 500.00),
       (1, 2, now() + interval '1 day 2 hours', 900.00),
       (2, 1, now() + interval '2 days', 450.00);
