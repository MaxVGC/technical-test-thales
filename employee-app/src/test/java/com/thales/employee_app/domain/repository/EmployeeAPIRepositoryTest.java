package com.thales.employee_app.domain.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.web.client.RestTemplate;

import com.thales.employee_app.domain.Employee;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeAPIRepositoryTest {

    @InjectMocks
    private EmployeeAPIRepository employeeAPIRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testFindAllNotNull() {
        Employee emp = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();

        Mockito.when(restTemplate.getForObject(EmployeeAPIRepository.API_URL, EmployeeListResponseDTO.class))
                .thenReturn(new EmployeeListResponseDTO("success",List.of(emp),"ad"));
        
        List<Employee> employees = employeeAPIRepository.findAll();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    void testFindAllNull() {
        Mockito.when(restTemplate.getForObject(EmployeeAPIRepository.API_URL, EmployeeListResponseDTO.class))
                .thenReturn(null);
        
        List<Employee> employees = employeeAPIRepository.findAll();
        assertNull(employees);
    }

    @Test
    void testFindAllRuntimeException() {
        Mockito.when(restTemplate.getForObject(EmployeeAPIRepository.API_URL, EmployeeListResponseDTO.class))
                .thenThrow(new RuntimeException());
        
        assertThrows(RuntimeException.class, () -> employeeAPIRepository.findAll());
    }

    @Test
    void testFindByIdNotNull() {
        Employee emp = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();

        Mockito.when(restTemplate.getForObject(EmployeeAPIRepository.API_URL_BY_ID + 1, EmployeeResponseDTO.class))
                .thenReturn(new EmployeeResponseDTO("success",emp,"ad"));
        
        Employee employee = employeeAPIRepository.findById(1L);
        assertNotNull(employee);
    }

    @Test
    void testFindByIdNull() {
        Mockito.when(restTemplate.getForObject(EmployeeAPIRepository.API_URL_BY_ID + 1, EmployeeResponseDTO.class))
                .thenReturn(null);
        
        Employee employee = employeeAPIRepository.findById(1L);
        assertNull(employee);
    }

    @Test
    void testFindByIdRuntimeException() {
        Mockito.when(restTemplate.getForObject(EmployeeAPIRepository.API_URL_BY_ID + 1, EmployeeResponseDTO.class))
                .thenThrow(new RuntimeException());
        
        assertThrows(RuntimeException.class, () -> employeeAPIRepository.findById(1L));
    }
   
}