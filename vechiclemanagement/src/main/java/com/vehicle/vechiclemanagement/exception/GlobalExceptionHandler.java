package com.vehicle.vechiclemanagement.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        if (ex.getMessage().equals("Invalid email or password")) {
            return new ResponseEntity<>("Invalid email or password. Please try again.", HttpStatus.UNAUTHORIZED);
        } else if (ex.getMessage().equals("Email already exists")) {
            return new ResponseEntity<>("Email already exists. Please use a different email.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}