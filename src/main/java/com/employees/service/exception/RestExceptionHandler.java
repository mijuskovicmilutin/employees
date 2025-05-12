package com.employees.service.exception;

import com.employees.service.exception.dto.BadRequestException;
import com.employees.service.exception.dto.OptimisticLockingException;
import com.employees.service.exception.dto.ResourceNotFoundException;
import com.employees.service.exception.dto.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ex.printStackTrace();
        RestError error = new RestError(
                ex.getMessage(),
                "The requested resource was not found.",
                String.valueOf(HttpStatus.NOT_FOUND.value())
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestError> handleBadRequestException(BadRequestException ex) {
        ex.printStackTrace();
        RestError error = new RestError(
                ex.getMessage(),
                "Bad request. Please verify the request and try again.",
                String.valueOf(HttpStatus.BAD_REQUEST.value())
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticLockingException.class)
    public ResponseEntity<RestError> handleOptimisticLockingException(OptimisticLockingException ex) {
        ex.printStackTrace();
        RestError error = new RestError(
                ex.getMessage(),
                "The resource was modified by another user. Please refresh and try again.",
                String.valueOf(HttpStatus.CONFLICT.value())
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestError> handleGenericException(Exception ex) {
        ex.printStackTrace();
        RestError error = new RestError(
                ex.getMessage(),
                "An unexpected error occurred. Please try again later.",
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
