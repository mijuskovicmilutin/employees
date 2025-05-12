package com.employees.service.utils;

import com.employees.service.model.Department;
import com.employees.service.model.Employee;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecifications {

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, cb) ->
                firstName == null ? cb.conjunction() : cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<Employee> hasLastName(String lastName) {
        return (root, query, cb) ->
                lastName == null ? cb.conjunction() : cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<Employee> hasPersonalId(String personalId) {
        return (root, query, cb) ->
                personalId == null ? cb.conjunction() :
                        cb.equal(root.get("personalId"), personalId);
    }

    public static Specification<Employee> hasEmail(String email) {
        return (root, query, cb) ->
                email == null ? cb.conjunction() :
                        cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Employee> inDepartment(String departmentName) {
        return (root, query, cb) -> {
            if (departmentName == null) return cb.conjunction();

            Join<Employee, Department> departmentJoin = root.join("department", JoinType.LEFT);
            return cb.like(cb.lower(departmentJoin.get("name")), "%" + departmentName.toLowerCase() + "%");
        };
    }

    public static Specification<Employee> hasStartDateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start == null && end == null) {
                return cb.conjunction();
            }

            Path<LocalDateTime> createdDate = root.get("createdDate");

            if (start != null && end != null) {
                return cb.between(createdDate,
                        start.atStartOfDay(),
                        end.atTime(LocalTime.MAX));
            } else if (start != null) {
                return cb.greaterThanOrEqualTo(createdDate, start.atStartOfDay());
            } else {
                return cb.lessThanOrEqualTo(createdDate, end.atTime(LocalTime.MAX));
            }
        };
    }

    public static Specification<Employee> hasTeamLeadWithName(String firstName, String lastName) {
        return (root, query, cb) -> {
            if (firstName == null && lastName == null) return cb.conjunction();

            Join<Employee, Department> departmentJoin = root.join("department", JoinType.LEFT);
            Join<Department, Employee> teamLeadJoin = departmentJoin.join("teamLead", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null) {
                predicates.add(cb.like(cb.lower(teamLeadJoin.get("firstName")), "%" + firstName.toLowerCase() + "%"));
            }
            if (lastName != null) {
                predicates.add(cb.like(cb.lower(teamLeadJoin.get("lastName")), "%" + lastName.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
