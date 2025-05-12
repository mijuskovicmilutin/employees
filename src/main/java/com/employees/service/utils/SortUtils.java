package com.employees.service.utils;

import com.employees.service.dto.EmployeeSearchCriteria;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class SortUtils {

    private static final Map<String, String> SORT_FIELD_MAPPINGS = Map.ofEntries(
            Map.entry("firstName", "firstName"),
            Map.entry("lastName", "lastName"),
            Map.entry("email", "email"),
            Map.entry("personalId", "personalId"),
            Map.entry("startDateFrom", "createdDate"),
            Map.entry("startDateTo", "createdDate"),

            Map.entry("departmentName", "department.name"),
            Map.entry("teamLeadFirstName", "department.teamLead.firstName"),
            Map.entry("teamLeadLastName", "department.teamLead.lastName")
    );

    public static Sort createSort(String sortBy, EmployeeSearchCriteria.SortDirection direction) {
        if (sortBy == null) {
            return Sort.unsorted();
        }

        String sortProperty = SORT_FIELD_MAPPINGS.getOrDefault(sortBy, sortBy);
        Sort.Direction sortDir = direction == EmployeeSearchCriteria.SortDirection.DESC ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        return Sort.by(sortDir, sortProperty);
    }
}
