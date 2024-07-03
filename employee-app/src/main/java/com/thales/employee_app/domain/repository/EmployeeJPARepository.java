package com.thales.employee_app.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thales.employee_app.domain.Employee;


/**
 * This interface represents a repository for managing Employee entities in a JPA-based data source.
 */
@Repository
public interface EmployeeJPARepository extends JpaRepository<Employee, Long>{
    
    /**
     * Retrieves an Employee entity by its ID.
     *
     * @param id The ID of the Employee entity to retrieve.
     * @return An Optional containing the Employee entity, or an empty Optional if not found.
     */
    Optional<Employee> findById(Long id);
    
    /**
     * Retrieves all Employee entities.
     *
     * @return A List containing all Employee entities.
     */
    List<Employee> findAll();

}
