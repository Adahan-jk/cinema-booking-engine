package com.cinema.catalog;

import java.time.OffsetDateTime;

public class ApiError {
    private final int status;
    private final String message;
    private final OffsetDateTime timestamp;

    public ApiError(int status, String message){
        this.status = status;
        this.message = message;
        this.timestamp = OffsetDateTime.now();
    }

    public int getStatus(){return status;}

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
