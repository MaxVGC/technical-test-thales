package com.thales.employee_app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an employee in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private Long id;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("employee_salary")
    private Long employeeSalary;

    @JsonProperty("employee_age")
    private Integer employeeAge;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("employee_anual_salary")
    private Long employeeAnualSalary;

    /**
     * Calculates and returns the annual salary of the employee.
     *
     * @return The annual salary of the employee.
     */
    public Long getEmployeeAnualSalary(){
        return this.employeeSalary * 12;
    }

    /**
     * Sets the annual salary of the employee and updates the monthly salary accordingly.
     *
     * @param employeeAnualSalary The annual salary to set for the employee.
     */
    public void setEmployeeAnualSalary(Long employeeAnualSalary){
        this.employeeAnualSalary = employeeAnualSalary;
        this.employeeSalary = employeeAnualSalary / 12;
    }
}
