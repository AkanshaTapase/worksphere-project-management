package com.worksphere.worksphere.service;

import com.worksphere.worksphere.entity.Employee;
import com.worksphere.worksphere.exception.ResourceNotFoundException;
import com.worksphere.worksphere.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor Injection
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Employee not found with id: " + id));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Long id, Employee updatedEmployee){
        Employee existingEmployee=getEmployeeById(id);
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());


        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id){

        employeeRepository.deleteById(id);
    }
}
