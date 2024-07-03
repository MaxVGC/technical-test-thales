package com.thales.employee_app.infrastructure.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thales.employee_app.application.dto.EmployeesListDTO;
import com.thales.employee_app.application.services.EmployeeService;
import com.thales.employee_app.domain.Employee;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller class for handling employee-related API requests.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The ResponseEntity containing the employee information.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable @NotNull(message = "{message.api.id.notNull}") @Pattern(regexp = "^[0-9]+$", message = "{message.api.id.notValid}") String id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(Long.parseLong(id)));
    }

    /**
     * Retrieves all employees.
     *
     * @return The ResponseEntity containing the list of employees.
     */
    @GetMapping("/all")
    public ResponseEntity<EmployeesListDTO> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
