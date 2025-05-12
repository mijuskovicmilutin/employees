package com.employees.service.dto.employee;

import com.employees.service.model.embedded.EmployeeRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Complete employee details including personal and employment data.")
public class EmployeeFullDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String personalId;

    private String email;

    private EmployeeRole role;

    private Long departmentId;

    private String departmentName;

    private Long teamLeadId;

    private String teamLeadName;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    private Long version;
}
