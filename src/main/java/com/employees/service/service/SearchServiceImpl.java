package com.employees.service.service;

import com.employees.service.dto.EmployeeSearchCriteria;
import com.employees.service.dto.employee.EmployeeFullDto;
import com.employees.service.model.Employee;
import com.employees.service.repository.EmployeeRepo;
import com.employees.service.utils.EmployeeMappingUtils;
import com.employees.service.utils.SearchSpecifications;
import com.employees.service.utils.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final EmployeeRepo employeeRepo;

    @Transactional(readOnly = true)
    public Page<EmployeeFullDto> searchEmployees(EmployeeSearchCriteria criteria, Pageable pageable) {
        Specification<Employee> spec = Specification.where(null);

        if (criteria.getFirstName() != null) {
            spec = spec.and(SearchSpecifications.hasFirstName(criteria.getFirstName()));
        }
        if (criteria.getLastName() != null) {
            spec = spec.and(SearchSpecifications.hasLastName(criteria.getLastName()));
        }
        if (criteria.getPersonalId() != null) {
            spec = spec.and(SearchSpecifications.hasPersonalId(criteria.getPersonalId()));
        }
        if (criteria.getEmail() != null) {
            spec = spec.and(SearchSpecifications.hasEmail(criteria.getEmail()));
        }
        if (criteria.getStartDateFrom() != null || criteria.getStartDateTo() != null) {
            spec = spec.and(SearchSpecifications.hasStartDateBetween(
                    criteria.getStartDateFrom(),
                    criteria.getStartDateTo()
            ));
        }

        if (criteria.getDepartmentName() != null) {
            spec = spec.and(SearchSpecifications.inDepartment(criteria.getDepartmentName()));
        }
        if (criteria.getTeamLeadFirstName() != null || criteria.getTeamLeadLastName() != null) {
            spec = spec.and(SearchSpecifications.hasTeamLeadWithName(
                    criteria.getTeamLeadFirstName(),
                    criteria.getTeamLeadLastName()));
        }

        Sort sort = SortUtils.createSort(criteria.getSortBy(), criteria.getSortDirection());
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        return employeeRepo.findAll(spec, sortedPageable).map(EmployeeMappingUtils::mapToFullDto);
    }
}
