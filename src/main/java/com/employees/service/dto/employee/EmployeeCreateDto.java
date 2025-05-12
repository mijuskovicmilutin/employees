package com.employees.service.dto.employee;

import com.employees.service.model.embedded.EmployeeRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Data needed to create a new employee.")
public class EmployeeCreateDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String personalId;

    @Email
    private String email;

    @NotNull
    private EmployeeRole role;

    @NotNull
    private Long departmentId;
}
