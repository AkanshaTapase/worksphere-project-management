
package com.worksphere.worksphere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksphere.worksphere.dto.EmployeeRequestDTO;
import com.worksphere.worksphere.dto.EmployeeResponseDTO;
import com.worksphere.worksphere.entity.Employee;
import com.worksphere.worksphere.exception.DuplicateEmployeeIdException;
import com.worksphere.worksphere.exception.ResourceNotFoundException;
import com.worksphere.worksphere.repository.EmployeeRepository;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO dto) {

        if (employeeRepository.existsByEmpId(dto.getEmpId())) {
            throw new DuplicateEmployeeIdException(
                    "Employee already exists with emp_id: " + dto.getEmpId());
        }

        Employee employee = new Employee();

        employee.setEmpId(dto.getEmpId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());

        Employee saved = employeeRepository.save(employee);

        return convertToDTO(saved);
    }

    // READ ALL
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // READ BY EMPLOYEE ID
    public EmployeeResponseDTO getEmployeeById(String empId) {

        Employee employee = findEmployee(empId);

        return convertToDTO(employee);
    }

    // UPDATE
    public EmployeeResponseDTO updateEmployee(
            String empId, EmployeeRequestDTO dto) {
        
        
        Employee employee = findEmployee(empId);

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());
        Employee updated = employeeRepository.save(employee);

        return convertToDTO(updated);
    }

    // DELETE
    public void deleteEmployee(String empId) {

        Employee employee = findEmployee(empId);

        employeeRepository.delete(employee);
    }

    // Find employee or throw exception
    private Employee findEmployee(String empId) {

        return employeeRepository.findByEmpId(empId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Employee not found with emp_id: " + empId));
    }

    // Convert Entity into Response DTO
    private EmployeeResponseDTO convertToDTO(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(employee.getId());
        dto.setEmpId(employee.getEmpId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setDesignation(employee.getDesignation());
        return dto;
    }
}
