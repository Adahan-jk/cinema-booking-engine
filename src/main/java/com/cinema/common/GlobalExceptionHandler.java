package com.cinema.common;

import com.cinema.booking.HoldExpiredException;
import com.cinema.booking.SeatTakenException;
import com.cinema.booking.SessionNotFoundException;
import com.cinema.booking.TicketNotConfirmableException;
import com.cinema.booking.TicketNotFoundException;
import com.cinema.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> notFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(404, "Ресурс не найден"));
    }

    @ExceptionHandler({SessionNotFoundException.class, TicketNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ApiError> entityNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(404, ex.getMessage()));
    }

    @ExceptionHandler(SeatTakenException.class)
    public ResponseEntity<ApiError> seatTaken(SeatTakenException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(409, ex.getMessage()));
    }

    @ExceptionHandler(TicketNotConfirmableException.class)
    public ResponseEntity<ApiError> notConfirmable(TicketNotConfirmableException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(409, ex.getMessage()));
    }

    @ExceptionHandler(HoldExpiredException.class)
    public ResponseEntity<ApiError> holdExpired(HoldExpiredException ex) {
        return ResponseEntity.status(HttpStatus.GONE).body(new ApiError(410, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalidRequest(MethodArgumentNotValidException ex) {
        String field = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .orElse("Некорректный запрос");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(400, field));
    }
}
