package com.worksphere.worksphere.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksphere.worksphere.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmpId(String empId);

    boolean existsByEmpId(String empId);
}
