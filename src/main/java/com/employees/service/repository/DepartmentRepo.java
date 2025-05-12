package com.employees.service.repository;

import com.employees.service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    boolean existsByName(String name);

    Optional<Department> findByTeamLeadId(Long id);

    Optional<Department> findByName(String benchDepartmentName);
}
