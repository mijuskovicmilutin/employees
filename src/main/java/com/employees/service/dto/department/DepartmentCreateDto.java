package com.employees.service.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Data needed to create a new department.")
public class DepartmentCreateDto {

    @NotBlank
    private String name;
}
