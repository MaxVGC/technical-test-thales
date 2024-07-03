package com.thales.employee_app.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thales.employee_app.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents a repository for accessing employee data from an API.
 */
@Repository
public class EmployeeAPIRepository {
    static final String API_URL = "http://dummy.restapiexample.com/api/v1/employees";
    static final String API_URL_BY_ID = "http://dummy.restapiexample.com/api/v1/employee/";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Retrieves a list of all employees from the API.
     *
     * @return A list of Employee objects representing all employees.
     * @throws RuntimeException if an error occurs while making the API request.
     */
    public List<Employee> findAll() {
        try {
            EmployeeListResponseDTO response = restTemplate.getForObject(API_URL, EmployeeListResponseDTO.class);
            return response != null ? response.getData() : null;
        } catch (Exception e) {
            throw new RuntimeException("message.api.tooManyRequests");
        }
    }

    /**
     * Finds an employee by their ID from the API.
     *
     * @param id the ID of the employee to find
     * @return the employee with the specified ID, or null if not found
     * @throws RuntimeException if an error occurs while making the API request
     */
    public Employee findById(Long id) {
        try{
            EmployeeResponseDTO response = restTemplate.getForObject(API_URL_BY_ID + id, EmployeeResponseDTO.class);
            return response != null ? response.getData() : null;
        } catch (Exception e) {
            throw new RuntimeException("message.api.tooManyRequests");
        }
    }
}

/**
 * Represents a response DTO for employee list.
 */
@Getter
@AllArgsConstructor
class EmployeeListResponseDTO {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<Employee> data;

    @JsonProperty("message")
    private String message;
}

/**
 * Represents the response DTO for an employee.
 */
@Getter
@AllArgsConstructor
class EmployeeResponseDTO {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Employee data;

    @JsonProperty("message")
    private String message;
}