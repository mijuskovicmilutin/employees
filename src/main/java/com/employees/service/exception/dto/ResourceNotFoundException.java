package com.employees.service.exception.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource could not be found")
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3381250967767715304L;

    public ResourceNotFoundException() {
        super("Resource could not be found");
    }

    public ResourceNotFoundException(String msg) {

        super(msg);

    }

}
