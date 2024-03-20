package ru.youmiteru.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SeasonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExecption(SeasonNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
            "Season not found",
            System.currentTimeMillis()
        );

        //return 404
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExecption(TitleNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
            "Title not found",
            System.currentTimeMillis()
        );

        //return 404
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExecption(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
            "User not found",
            System.currentTimeMillis()
        );

        //return 404
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
