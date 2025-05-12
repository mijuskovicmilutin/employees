package com.employees.service.utils;

import com.employees.service.dto.department.DepartmentCreateDto;
import com.employees.service.dto.department.DepartmentFullDto;
import com.employees.service.dto.department.DepartmentShortDto;
import com.employees.service.model.Department;
import com.employees.service.model.Employee;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMappingUtils {

    public static DepartmentShortDto mapToShortDto(Department department) {
        if (department == null) return null;
        DepartmentShortDto shortDto = new DepartmentShortDto();
        BeanUtils.copyProperties(department, shortDto);
        if (department.getTeamLead() != null) {
            shortDto.setTeamLeadId(department.getTeamLead().getId());
            shortDto.setTeamLeadName(department.getTeamLead().getFirstName() + " " + department.getTeamLead().getLastName());
        }
        return shortDto;
    }

    public static DepartmentFullDto mapToFullDto(Department department, List<Employee> members) {
        if (department == null) return null;
        DepartmentFullDto fullDto = new DepartmentFullDto();
        BeanUtils.copyProperties(department, fullDto);

        if (department.getTeamLead() != null) {
            fullDto.setTeamLeadId(department.getTeamLead().getId());
            fullDto.setTeamLeadName(department.getTeamLead().getFirstName() + " " + department.getTeamLead().getLastName());
        }

        fullDto.setMembers(members.stream()
                .map(m -> {
                    DepartmentFullDto.MemberDto memberDto = fullDto.new MemberDto();
                    memberDto.setMemberId(m.getId());
                    memberDto.setMemberName(m.getFirstName() + " " + m.getLastName());
                    return memberDto;
                })
                .collect(Collectors.toList()));

        return fullDto;
    }

    public static Department mapToModel(DepartmentCreateDto createDto) {
        Department department = new Department();
        department.setName(createDto.getName());
        return department;
    }
}
