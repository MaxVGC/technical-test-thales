package com.thales.employee_app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.thales.employee_app.domain.repository.EmployeeJPARepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class EmployeeTest {
    @Autowired
    private EmployeeJPARepository employeeJPARepository;

    @Test
    void testSaveEmployee() {
        Employee employee = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();
        Employee savedEmployee = employeeJPARepository.save(employee);
        Assertions.assertNotNull(savedEmployee);
        Assertions.assertNotNull(savedEmployee.getId());
    }

    @Test
    void testFindEmployeeById() {
        Employee employee = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();
        employeeJPARepository.save(employee);
        Employee foundEmployee = employeeJPARepository.findById(1L).orElse(null);
        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals("John Doe", foundEmployee.getEmployeeName());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();
        employeeJPARepository.save(employee);
        employeeJPARepository.deleteById(1L);
        Employee foundEmployee = employeeJPARepository.findById(1L).orElse(null);
        Assertions.assertNull(foundEmployee);
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = Employee.builder()
                .id(1L)
                .employeeName("John Doe")
                .employeeSalary(50000L)
                .employeeAge(30)
                .profileImage("profile.jpg")
                .build();
        employeeJPARepository.save(employee);
        Employee foundEmployee = employeeJPARepository.findById(1L).orElse(null);
        foundEmployee.setEmployeeName("Jane Doe");
        employeeJPARepository.save(foundEmployee);
        Employee updatedEmployee = employeeJPARepository.findById(1L).orElse(null);
        Assertions.assertNotNull(updatedEmployee);
        Assertions.assertEquals("Jane Doe", updatedEmployee.getEmployeeName());
    }

    @Test
    void testEmployeeId() {
        Employee employee = new Employee();
        employee.setId(1L);
        Assertions.assertEquals(1L, employee.getId());
    }

    @Test
    void testEmployeeName() {
        Employee employee = new Employee();
        employee.setEmployeeName("John Doe");
        Assertions.assertEquals("John Doe", employee.getEmployeeName());
    }

    @Test
    void testEmployeeSalary() {
        Employee employee = new Employee();
        employee.setEmployeeSalary(50000L);
        Assertions.assertEquals(50000L, employee.getEmployeeSalary());
        Assertions.assertEquals(600000L, employee.getEmployeeAnualSalary());
    }

    @Test
    void testEmployeeAge() {
        Employee employee = new Employee();
        employee.setEmployeeAge(30);
        Assertions.assertEquals(30, employee.getEmployeeAge());
    }

    @Test
    void testProfileImage() {
        Employee employee = new Employee();
        employee.setProfileImage("profile.jpg");
        Assertions.assertEquals("profile.jpg", employee.getProfileImage());
    }

    @Test
    void testEmployeeAnualSalary() {
        Employee employee = new Employee();
        employee.setEmployeeAnualSalary(600000L);
        Assertions.assertEquals(600000L, employee.getEmployeeAnualSalary());
        Assertions.assertEquals(50000L, employee.getEmployeeSalary());
    }
}
