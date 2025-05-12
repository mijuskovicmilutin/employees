package com.employees.service.service;

import com.employees.service.dto.employee.EmployeeCreateDto;
import com.employees.service.dto.employee.EmployeeFullDto;
import com.employees.service.dto.employee.EmployeeShortDto;
import com.employees.service.dto.employee.EmployeeUpdateDto;
import com.employees.service.exception.dto.BadRequestException;
import com.employees.service.exception.dto.ResourceNotFoundException;
import com.employees.service.model.Department;
import com.employees.service.model.Employee;
import com.employees.service.repository.DepartmentRepo;
import com.employees.service.repository.EmployeeRepo;
import com.employees.service.utils.EmployeeMappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;

    @Override
    public List<EmployeeShortDto> findAll() {
        return employeeRepo.findAll().stream().map(EmployeeMappingUtils::mapToShortDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeFullDto findById(Long id) {
        return EmployeeMappingUtils.mapToFullDto(
                employeeRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id))
        );
    }

    @Override
    @Transactional
    public EmployeeFullDto create(EmployeeCreateDto createDto) {
        Department department = departmentRepo.findById(createDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + createDto.getDepartmentId()));
        if (employeeRepo.existsByPersonalId(createDto.getPersonalId())) {
            throw new BadRequestException("Employee with personal ID " + createDto.getPersonalId() + " already exists");
        }
        return EmployeeMappingUtils.mapToFullDto(
                employeeRepo.save(EmployeeMappingUtils.mapToModel(createDto, department))
        );
    }

    @Override
    @Transactional
    public EmployeeFullDto update(Long id, EmployeeUpdateDto updateDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: " + id));
        Department department = departmentRepo.findById(updateDto.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException("Department not found with id: " + updateDto.getDepartmentId()));
        return EmployeeMappingUtils.mapToFullDto(
                employeeRepo.save(EmployeeMappingUtils.updateModel(employee, updateDto, department))
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        Department department = employee.getDepartment();
        if (department != null && employee.equals(department.getTeamLead())) {
            department.setTeamLead(null);
            departmentRepo.save(department);
        }
        employeeRepo.delete(employee);
    }
}
