package com.thales.employee_app.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thales.employee_app.application.dto.EmployeesListDTO;
import com.thales.employee_app.domain.Employee;
import com.thales.employee_app.domain.repository.EmployeeAPIRepository;
import com.thales.employee_app.domain.repository.EmployeeJPARepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeAPIRepository employeeAPIRepository;
    @Autowired
    private EmployeeJPARepository employeeJPARepository;

    private Long lastUpdate = null;

    public Employee getEmployeeById(Long id) {
        Employee employee = employeeJPARepository.findById(id).orElse(null);
        if (employee == null) {
            employee = employeeAPIRepository.findById(id);
            if (employee != null) {
                lastUpdate = System.currentTimeMillis() / 1000;
                employeeJPARepository.save(employee);
            }
        }
        if (employee == null) {
            throw new RuntimeException("message.employee.notfound");
        }
        return employee;
    }

    @Override
    public EmployeesListDTO getAllEmployees() {
        try {
            List<Employee> employees = employeeAPIRepository.findAll();
            List<Long> savedEmployees = employeeJPARepository.findAll().stream().map(Employee::getId).toList();

            for (Employee employee : employees) {
                if (!savedEmployees.contains(employee.getId())) {
                    employeeJPARepository.save(employee);
                }
            }
            lastUpdate = System.currentTimeMillis() / 1000;
            return new EmployeesListDTO(employees, lastUpdate);
        } catch (Exception e) {
            List<Employee> employees = employeeJPARepository.findAll();
            if(employees.isEmpty()) {
                throw new RuntimeException("message.api.tooManyRequests");
            }
            return new EmployeesListDTO(employees, lastUpdate);
        }
    }


}
