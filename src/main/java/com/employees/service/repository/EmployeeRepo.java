package com.employees.service.repository;

import com.employees.service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> findAllByDepartmentId(Long id);

    boolean existsByPersonalId(String personalId);

    List<Employee> findByDepartmentId(Long departmentId);
}
