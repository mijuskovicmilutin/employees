package com.employees.service.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Data needed to update a department, includes team lead reference.")
public class DepartmentUpdateDto {

    @NotBlank
    private String name;

    private Long teamLeadId;

    @NotNull
    private Long version;
}
