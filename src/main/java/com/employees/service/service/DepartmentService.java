package com.employees.service.service;

import com.employees.service.dto.department.DepartmentCreateDto;
import com.employees.service.dto.department.DepartmentFullDto;
import com.employees.service.dto.department.DepartmentShortDto;
import com.employees.service.dto.department.DepartmentUpdateDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentShortDto> findAll();

    DepartmentFullDto findById(Long id);

    DepartmentShortDto create(DepartmentCreateDto createDto);

    DepartmentShortDto update(Long id, DepartmentUpdateDto updateDto);

    void deleteById(Long id);
}
