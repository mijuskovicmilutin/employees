package com.employees.service.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Basic department info.")
public class DepartmentShortDto {

    private Long id;

    private String name;

    private Long teamLeadId;

    private String teamLeadName;

    private Long version;
}
