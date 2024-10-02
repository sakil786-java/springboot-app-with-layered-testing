package com.test.TestCases.repositories;

import com.test.TestCases.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String email);
    List<Employee>findByName(String name);
}
