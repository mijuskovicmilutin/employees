package com.employees.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeSearchCriteria {

    private String firstName;
    private String lastName;
    private String personalId;
    private String email;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;

    private String departmentName;
    private String teamLeadFirstName;
    private String teamLeadLastName;

    private String sortBy;
    private SortDirection sortDirection = SortDirection.ASC;

    public enum SortDirection {
        ASC, DESC
    }
}
