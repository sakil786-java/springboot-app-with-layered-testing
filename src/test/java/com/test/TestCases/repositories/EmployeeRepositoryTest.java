package com.test.TestCases.repositories;

import com.test.TestCases.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        // We will not set the ID manually. Let it be generated by the persistence provider.
        employee = Employee.builder()
                .name("Sakil")
                .email("sakil@gmail.com")
                .salary(100L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
        // Arrange, Given
        employeeRepository.save(employee); // Save the employee in the database

        // Act, When
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        // Assert, Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList() {
        // Given
        String email = "notPresent.123@gmail.com";

        // When
        List<Employee> employeeList = employeeRepository.findByEmail(email);

        // Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();
    }

    @Test
    void testFindByEmployeeId_whenIdIsFound_thenReturnEmployee() {
        // Save the employee in the database, and after saving, get the auto-generated ID
        Employee savedEmployee = employeeRepository.save(employee);

        // Given
        Long id = savedEmployee.getId(); // Fetch the auto-generated ID

        // When
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        // Then
        assertThat(employeeOptional).isPresent(); // Check if employee exists
        assertThat(employeeOptional.get()).isEqualTo(savedEmployee); // Compare the employee objects
    }

    @Test
    void testFindByName_whenNameIsPresent_thenReturnEmployee() {
        // Arrange, Given
        employeeRepository.save(employee); // Save the employee in the database

        // Act, When
        List<Employee> employeeList = employeeRepository.findByName(employee.getName());

        // Assert, Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getName()).isEqualTo(employee.getName());
    }

    @Test
    void testFindByName_whenNameIsNotFound_thenReturnEmptyEmployeeList() {

        // Given
        String name = "Sakil";

        // When
        List<Employee> employeeList = employeeRepository.findByName(name);

        // Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();
    }
}