package com.employees.service.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Basic employee info for Excel file.")
public class EmployeeShortDto {

    private String personalId;

    private String name;

    private String departmentName;

    private String teamLeadName;
}
