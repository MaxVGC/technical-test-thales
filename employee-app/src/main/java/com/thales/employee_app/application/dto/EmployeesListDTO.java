
package com.thales.employee_app.application.dto;

import java.util.List;

import com.thales.employee_app.domain.Employee;

/**
 * Represents a DTO (Data Transfer Object) for a list of employees.
 */
public record EmployeesListDTO(List<Employee> employees, Long lastUpdate) {

}