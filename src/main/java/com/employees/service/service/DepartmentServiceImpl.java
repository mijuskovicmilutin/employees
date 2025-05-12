package com.employees.service.service;

import com.employees.service.dto.department.DepartmentCreateDto;
import com.employees.service.dto.department.DepartmentFullDto;
import com.employees.service.dto.department.DepartmentShortDto;
import com.employees.service.dto.department.DepartmentUpdateDto;
import com.employees.service.exception.dto.BadRequestException;
import com.employees.service.exception.dto.ResourceNotFoundException;
import com.employees.service.model.Department;
import com.employees.service.model.Employee;
import com.employees.service.model.embedded.EmployeeRole;
import com.employees.service.repository.DepartmentRepo;
import com.employees.service.repository.EmployeeRepo;
import com.employees.service.utils.DepartmentMappingUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;

    public static final String BENCH_DEPARTMENT_NAME = "Bench";

    @Override
    public List<DepartmentShortDto> findAll() {
        return departmentRepo.findAll().stream().map(DepartmentMappingUtils::mapToShortDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentFullDto findById(Long id) {
        Department department = departmentRepo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Department not found with id: " + id));
        List<Employee> members = employeeRepo.findByDepartmentId(id);
        return DepartmentMappingUtils.mapToFullDto(department, members);
    }

    @Override
    @Transactional
    public DepartmentShortDto create(DepartmentCreateDto createDto) {
        if (departmentRepo.existsByName(createDto.getName())) {
            throw new BadRequestException("Department with name '" + createDto.getName() + "' already exists");
        }
        return DepartmentMappingUtils.mapToShortDto(departmentRepo.save(DepartmentMappingUtils.mapToModel(createDto)));
    }

    @Override
    @Transactional
    public DepartmentShortDto update(Long id, DepartmentUpdateDto updateDto) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        updateDepartmentName(department, updateDto.getName());
        updateTeamLead(department, updateDto.getTeamLeadId());
        department.setVersion(updateDto.getVersion());

        return DepartmentMappingUtils.mapToShortDto(departmentRepo.save(department));
    }

    private void updateDepartmentName(Department department, String newName) {
        if (department.getName().equals(newName)) return;
        if (departmentRepo.existsByName(newName)) {
            throw new BadRequestException("Department name already exists");
        }
        department.setName(newName);
    }

    private void updateTeamLead(Department department, Long newTeamLeadId) {
        if (newTeamLeadId == null) {
            removeCurrentTeamLeadIfExists(department);
            return;
        }

        Employee newTeamLead = getValidTeamLead(department, newTeamLeadId);
        if (newTeamLead.equals(department.getTeamLead())) return;

        reassignTeamLead(department, newTeamLead);
    }

    private void removeCurrentTeamLeadIfExists(Department department) {
        Optional.ofNullable(department.getTeamLead())
                .ifPresent(teamLead -> {
                    department.setTeamLead(null);
                    teamLead.setRole(EmployeeRole.MEMBER);
                    employeeRepo.save(teamLead);
                });
    }

    private Employee getValidTeamLead(Department department, Long teamLeadId) {
        Employee teamLead = employeeRepo.findById(teamLeadId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + teamLeadId));

        if (teamLead.getDepartment() == null || !teamLead.getDepartment().getId().equals(department.getId())) {
            Optional.ofNullable(teamLead.getDepartment())
                    .filter(oldDept -> teamLead.equals(oldDept.getTeamLead()))
                    .ifPresent(oldDept -> {
                        oldDept.setTeamLead(null);
                        departmentRepo.save(oldDept);
                    });

            teamLead.setDepartment(department);
            employeeRepo.save(teamLead);
        }

        return teamLead;
    }

    private void reassignTeamLead(Department department, Employee newTeamLead) {
        departmentRepo.findByTeamLeadId(newTeamLead.getId())
                .filter(dept -> !dept.getId().equals(department.getId()))
                .ifPresent(dept -> {
                    dept.setTeamLead(null);
                    departmentRepo.save(dept);
                });

        removeCurrentTeamLeadIfExists(department);

        department.setTeamLead(newTeamLead);
        newTeamLead.setRole(EmployeeRole.TEAM_LEAD);
        employeeRepo.save(newTeamLead);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        List<Employee> employees = employeeRepo.findAllByDepartmentId(id);
        List<Employee> updatedEmployees = new ArrayList<>();

        employees.forEach(employee -> {
            Department bench = departmentRepo.findByName(BENCH_DEPARTMENT_NAME)
                    .orElseThrow(() -> new ResourceNotFoundException("Bench (default) Department not found."));

            employee.setDepartment(bench);
            if (employee.equals(department.getTeamLead())) {
                employee.setRole(EmployeeRole.MEMBER);
            }
            updatedEmployees.add(employee);
        });

        employeeRepo.saveAll(updatedEmployees);
        departmentRepo.delete(department);
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (!departmentRepo.existsByName(BENCH_DEPARTMENT_NAME)) {
            Department bench = new Department();
            bench.setName(BENCH_DEPARTMENT_NAME);
            departmentRepo.save(bench);
        }
    }
}
