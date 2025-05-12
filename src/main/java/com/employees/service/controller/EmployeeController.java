package com.employees.service.controller;


import com.employees.service.dto.EmployeeSearchCriteria;
import com.employees.service.dto.employee.EmployeeCreateDto;
import com.employees.service.dto.employee.EmployeeFullDto;
import com.employees.service.dto.employee.EmployeeShortDto;
import com.employees.service.dto.employee.EmployeeUpdateDto;
import com.employees.service.service.EmployeeService;
import com.employees.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;
    private final SearchService searchService;

    @Override
    public ResponseEntity<List<EmployeeShortDto>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @Override
    public ResponseEntity<EmployeeFullDto> findById(Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @Override
    public ResponseEntity<EmployeeFullDto> create(EmployeeCreateDto createDto) {
        return ResponseEntity.ok(employeeService.create(createDto));
    }

    @Override
    public ResponseEntity<EmployeeFullDto> update(Long id, EmployeeUpdateDto updateDto) {
        return ResponseEntity.ok(employeeService.update(id, updateDto));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<EmployeeFullDto>> searchEmployees(EmployeeSearchCriteria criteria, Pageable pageable) {
        return ResponseEntity.ok(searchService.searchEmployees(criteria, pageable));
    }
}
