package com.employees.service.utils;

import com.employees.service.dto.employee.EmployeeCreateDto;
import com.employees.service.dto.employee.EmployeeFullDto;
import com.employees.service.dto.employee.EmployeeShortDto;
import com.employees.service.dto.employee.EmployeeUpdateDto;
import com.employees.service.model.Department;
import com.employees.service.model.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeMappingUtils {
    public static EmployeeShortDto mapToShortDto(Employee employee) {
        if (employee == null) return null;
        EmployeeShortDto shortDto = new EmployeeShortDto();
        shortDto.setPersonalId(employee.getPersonalId());
        shortDto.setName(employee.getFirstName() + " " + employee.getLastName());
        if (employee.getDepartment() != null) {
            shortDto.setDepartmentName(employee.getDepartment().getName());
            if (employee.getDepartment().getTeamLead() != null) {
                shortDto.setTeamLeadName(employee.getDepartment().getTeamLead().getFirstName() + " "
                        + employee.getDepartment().getTeamLead().getLastName());
            }
        }
        return shortDto;
    }

    public static EmployeeFullDto mapToFullDto(Employee employee) {
        if (employee == null) return null;
        EmployeeFullDto fullDto = new EmployeeFullDto();
        BeanUtils.copyProperties(employee, fullDto);
        if (employee.getDepartment() != null) {
            fullDto.setDepartmentId(employee.getDepartment().getId());
            fullDto.setDepartmentName(employee.getDepartment().getName());
            if (employee.getDepartment().getTeamLead() != null) {
                fullDto.setTeamLeadId(employee.getDepartment().getTeamLead().getId());
                fullDto.setTeamLeadName(employee.getDepartment().getTeamLead().getFirstName() + " "
                        + employee.getDepartment().getTeamLead().getLastName());
            }
        }
        return fullDto;
    }

    public static Employee mapToModel(EmployeeCreateDto createDto, Department department) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(createDto, employee);
        employee.setDepartment(department);
        return employee;
    }

    public static Employee updateModel(Employee employee, EmployeeUpdateDto updateDto, Department department) {
        employee.setFirstName(updateDto.getFirstName());
        employee.setLastName(updateDto.getLastName());
        employee.setRole(updateDto.getRole());
        employee.setEmail(updateDto.getEmail());
        employee.setDepartment(department);
        employee.setVersion(updateDto.getVersion());
        return employee;
    }
}
