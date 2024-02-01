package ru.youmiteru.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ErrorResponse> handleExecption(SeasonNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
            "Season not found",
            System.currentTimeMillis()
        );

        //return 404
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
