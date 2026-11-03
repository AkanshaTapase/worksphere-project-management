
package com.worksphere.worksphere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksphere.worksphere.dto.EmployeeRequestDTO;
import com.worksphere.worksphere.dto.EmployeeResponseDTO;
import com.worksphere.worksphere.entity.Employee;
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

    Employee employee = new Employee();

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

    // READ BY ID
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = findEmployee(id);

        return convertToDTO(employee);
    }

    // UPDATE
    public EmployeeResponseDTO updateEmployee(
            Long id, EmployeeRequestDTO dto) {

        Employee employee = findEmployee(id);

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());
        Employee updated = employeeRepository.save(employee);

        return convertToDTO(updated);
    }

    // DELETE
    public void deleteEmployee(Long id) {

        Employee employee = findEmployee(id);

        employeeRepository.delete(employee);
    }

    // Find employee or throw exception
    private Employee findEmployee(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Employee not found with id: " + id));
    }

    // Convert Entity into Response DTO
    private EmployeeResponseDTO convertToDTO(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setDesignation(employee.getDesignation());
        return dto;
    }
}
