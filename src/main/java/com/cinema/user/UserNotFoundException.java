package com.cinema.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("Пользователь " + userId + " не найден");
    }
}
