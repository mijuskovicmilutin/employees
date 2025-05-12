package com.employees.service.exception.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Not permitted for current user")
public class BadRequestException extends RuntimeException {

	@Serial
    private static final long serialVersionUID = 8010975539490218852L;

	public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException() {
        super("Bad request");
    }
}
