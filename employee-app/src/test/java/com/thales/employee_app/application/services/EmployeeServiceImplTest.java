package com.thales.employee_app.application.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.thales.employee_app.domain.Employee;
import com.thales.employee_app.domain.repository.EmployeeAPIRepository;
import com.thales.employee_app.domain.repository.EmployeeJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {
    @Mock
    private EmployeeAPIRepository employeeAPIRepository;
    @Mock
    private EmployeeJPARepository employeeJPARepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> apiEmployees = new ArrayList<>();
        apiEmployees.add(Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build());
        apiEmployees.add(Employee.builder()
                .id(2L)
                .employeeName("Jane Doe")
                .employeeSalary(60000L)
                .employeeAge(35)
                .profileImage("profile.jpg")
                .build());

        List<Employee> jpaEmployees = new ArrayList<>();
        jpaEmployees.add(Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build());
        jpaEmployees.add(Employee.builder()
                .id(3L)
                .employeeName("Jack Doe")
                .employeeSalary(70000L)
                .employeeAge(40)
                .profileImage("profile.jpg")
                .build());

        when(employeeAPIRepository.findAll()).thenReturn(apiEmployees);
        when(employeeJPARepository.findAll()).thenReturn(jpaEmployees);

        List<Employee> result = employeeService.getAllEmployees().employees();

        Assertions.assertEquals(apiEmployees, result);
        verify(employeeJPARepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetEmployeeById_existingEmployee() {
        Long employeeId = 1L;
        Employee employee = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();

        when(employeeJPARepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(employeeId);

        Assertions.assertEquals(employee, result);
        verify(employeeAPIRepository, never()).findById(anyLong());
        verify(employeeJPARepository, never()).save(any(Employee.class));
    }

    @Test
    void testGetEmployeeById_nonExistingEmployee() {
        Long employeeId = 1L;

        when(employeeJPARepository.findById(employeeId)).thenReturn(Optional.empty());
        when(employeeAPIRepository.findById(employeeId)).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(employeeId));
        verify(employeeJPARepository, never()).save(any(Employee.class));
    }

    @Test 
    void testGetAllEmployees_whenAPIFails() {
        List<Employee> jpaEmployees = new ArrayList<>();
        jpaEmployees.add(Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build());
        jpaEmployees.add(Employee.builder()
                .id(3L)
                .employeeName("Jack Doe")
                .employeeSalary(70000L)
                .employeeAge(40)
                .profileImage("profile.jpg")
                .build());

        when(employeeAPIRepository.findAll()).thenThrow(new RuntimeException());
        when(employeeJPARepository.findAll()).thenReturn(jpaEmployees);

        List<Employee> result = employeeService.getAllEmployees().employees();

        Assertions.assertEquals(jpaEmployees, result);
        verify(employeeJPARepository, never()).save(any(Employee.class));
    }
}