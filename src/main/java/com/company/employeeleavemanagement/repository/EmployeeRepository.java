package com.company.employeeleavemanagement.repository;

import com.company.employeeleavemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // No additional methods required for basic CRUD operations
}
