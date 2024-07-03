package com.thales.employee_app.application.services;

import com.thales.employee_app.application.dto.EmployeesListDTO;
import com.thales.employee_app.domain.Employee;

/**
 * The EmployeeService interface provides methods to retrieve employee information.
 */
public interface EmployeeService {
    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee.
     * @return The employee with the specified ID.
     */
    public Employee getEmployeeById(Long id);

    /**
     * Retrieves a list of all employees.
     *
     * @return A list of all employees.
     */
    public EmployeesListDTO getAllEmployees();
}
