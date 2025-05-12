package com.employees.service.service;

import com.employees.service.dto.employee.EmployeeCreateDto;
import com.employees.service.dto.employee.EmployeeFullDto;
import com.employees.service.dto.employee.EmployeeShortDto;
import com.employees.service.dto.employee.EmployeeUpdateDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeShortDto> findAll();

    EmployeeFullDto findById(Long id);

    EmployeeFullDto create(EmployeeCreateDto createDto);

    EmployeeFullDto update(Long id, EmployeeUpdateDto updateDto);

    void deleteById(Long id);
}
