package com.thales.employee_app.infrastructure.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.thales.employee_app.application.dto.EmployeesListDTO;
import com.thales.employee_app.application.services.EmployeeService;
import com.thales.employee_app.domain.Employee;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);
        mockMvc.perform(get("/api/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.employee_name").value("John Doe"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee);
        EmployeesListDTO employeesListDTO = new EmployeesListDTO(employees, null);
        when(employeeService.getAllEmployees()).thenReturn(employeesListDTO);

        mockMvc.perform(get("/api/v1/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].employee_name").value("John Doe"));
    }

    @Test
    void testGetEmployeeByIdWithNullId() throws Exception {
        mockMvc.perform(get("/api/v1/employee/"))
                .andExpect(status().isNotFound());
    }
}