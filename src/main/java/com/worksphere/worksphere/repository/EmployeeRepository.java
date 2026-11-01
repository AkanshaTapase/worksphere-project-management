package com.worksphere.worksphere.repository;

import com.worksphere.worksphere.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

}
