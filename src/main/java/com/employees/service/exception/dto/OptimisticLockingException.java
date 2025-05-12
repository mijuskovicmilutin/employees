package com.employees.service.exception.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Optimistic lock exception occurred")
public class OptimisticLockingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3381250967767715305L;

    public OptimisticLockingException() {
        super("Optimistic lock exception occurred");
    }

    public OptimisticLockingException(String msg) {
        super(msg);
    }

    public OptimisticLockingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
