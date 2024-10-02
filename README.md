# Spring Boot Application with Layered Testing

This project demonstrates a Spring Boot application following a layered architecture with comprehensive test cases for each layer: Controller, Service, and Repository. The goal is to ensure full coverage and proper isolation of unit and integration tests.




## Layers Covered
1. **Controller Layer**: Handles incoming REST API requests. It is tested using `MockMvc` for simulating HTTP requests without needing to start the entire server.
   
2. **Service Layer**: Contains the business logic and is tested using `JUnit` and `Mockito` to mock external dependencies, ensuring that service logic is isolated.

3. **Repository Layer**: Manages data persistence and is tested using `Spring Data JPA` with an embedded H2 database for isolated and transactional database tests.


## Technologies and Testing Tools Used

- **Spring Boot**: For building the application.
- **JUnit 5**: For writing unit tests.
- **Mockito**: For mocking dependencies in the service layer.
- **ModelMapper**: Used for mapping between objects. 
  - **InjectMocks**: Used to inject mock dependencies into the service class during testing, allowing for isolation of the service logic.
  - **Spy**: Partial mocking is used in cases where we need to call the real methods of the ModelMapper, but mock certain behaviors for specific cases.
- **Jacoco**: A plugin used in Maven to generate test coverage reports, helping to analyze which parts of the code are covered by tests.
  - **Function**: It provides a coverage report detailing the percentage of code tested, helping to ensure high coverage and quality.
- **WebTestClient**: Used in the Controller layer to test REST endpoints without starting the full server.
  - **Function**: It simulates HTTP requests and verifies the response of REST APIs, allowing you to test the controllers in isolation.
  
## Important Annotations

1. `@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)`:
   - This annotation is used in repository tests to prevent Spring from replacing the real database with an embedded one. It ensures that tests use the actual database configuration instead of a mock or in-memory database.

2. `@ExtendWith(MockitoExtension.class)`:
   - This annotation is used to extend the test class with the Mockito framework, enabling the use of annotations like `@Mock`, `@InjectMocks`, and `@Spy` for mocking and spying objects. It helps simplify unit tests by injecting mock dependencies automatically.

## Layers Covered

1. **Controller Layer**: Handles incoming REST API requests. Tested using `WebTestClient` for simulating HTTP requests without needing to start the entire server.
   
2. **Service Layer**: Contains the business logic and is tested using `JUnit` and `Mockito` to mock external dependencies like repositories, ensuring isolation.

   - `@InjectMocks` and `@Spy` are used to manage dependencies like ModelMapper within the service layer, allowing for flexible and partial mocking.

3. **Repository Layer**: Manages data persistence and is tested using `Spring Data JPA` with an embedded H2 database for isolated and transactional database tests. `@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)` is applied to maintain the real database configuration.

## Running the Tests
To run the test cases for all layers, use the following command:

```bash
mvn test


### Summary of Changes:
- **`InjectMocks` and `Spy`**: Used to manage the `ModelMapper` dependency and allow partial mocking during testing.
- **`Jacoco`**: Integrated to generate test coverage reports, helping track which code sections are covered by unit tests.
- **`WebTestClient`**: Tests the Controller layer by simulating HTTP requests.
- **`@AutoConfigureTestDatabase`**: Ensures the real database is used in repository tests, rather than an in-memory one.
- **`@ExtendWith(MockitoExtension.class)`**: Simplifies unit testing with Mockito by auto-injecting mocks and spies.


