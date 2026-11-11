
package com.worksphere.worksphere.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.worksphere.worksphere.dto.EmployeeRequestDTO;
import com.worksphere.worksphere.dto.EmployeeResponseDTO;
import com.worksphere.worksphere.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> addEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.addEmployee(dto));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // READ BY EMPLOYEE ID
    @GetMapping("/{emp_id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable("emp_id") String empId) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(empId));
    }

    // UPDATE
    @PutMapping("/{emp_id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable("emp_id") String empId,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(empId, dto));
    }

    // DELETE
    @DeleteMapping("/{emp_id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable("emp_id") String empId) {

        employeeService.deleteEmployee(empId);

        return ResponseEntity.noContent().build();
    }
}
