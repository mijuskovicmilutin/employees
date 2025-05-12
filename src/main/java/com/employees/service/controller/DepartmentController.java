package com.employees.service.controller;

import com.employees.service.dto.department.DepartmentCreateDto;
import com.employees.service.dto.department.DepartmentFullDto;
import com.employees.service.dto.department.DepartmentShortDto;
import com.employees.service.dto.department.DepartmentUpdateDto;
import com.employees.service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentApi {

    private final DepartmentService departmentService;

    @Override
    public ResponseEntity<List<DepartmentShortDto>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @Override
    public ResponseEntity<DepartmentFullDto> findById(Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @Override
    public ResponseEntity<DepartmentShortDto> create(DepartmentCreateDto createDto) {
        return ResponseEntity.ok(departmentService.create(createDto));
    }

    @Override
    public ResponseEntity<DepartmentShortDto> update(Long id, DepartmentUpdateDto updateDto) {
        return ResponseEntity.ok(departmentService.update(id, updateDto));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
