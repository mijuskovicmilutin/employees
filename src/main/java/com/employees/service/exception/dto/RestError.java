package com.employees.service.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Represents a global error in the REST API. Contains detailed information about the error including developer-specific and user-friendly messages, as well as optional sub-errors.")
public class RestError {


    @Schema(description = "Detailed message intended for developers.", example = "NullPointerException occurred in Service class.")
    private String developerMessage;


    @Schema(description = "User-friendly message.", example = "An unexpected error occurred. Please try again.")
    private String userMessage;


    @Schema(description = "Internal response code used to track specific types of errors.", example = "500")
    private String internalResponseCode = "";

    public RestError(final String developerMessage, final String userMessage, final String internalCode) {
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
        this.internalResponseCode = internalCode;
    }
}
