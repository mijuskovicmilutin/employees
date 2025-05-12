package com.employees.service.service;

import com.employees.service.dto.EmployeeSearchCriteria;
import com.employees.service.dto.employee.EmployeeFullDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    Page<EmployeeFullDto> searchEmployees(EmployeeSearchCriteria criteria, Pageable pageable);
}
