# tutorial-1-adpro

## Module 1
### Reflection 1

1. Clean Code Principles Applied:

    - Meaningful Names: All classes, methods, and variables are given descriptive names, making the code self-documenting and easy to understand.
    - Single Responsibility: Each controller method is responsible for a single operation, adhering to the Single Responsibility Principle.
    - Consistent Formatting: The code consistently uses indentation and spacing, enhancing its readability.

2. Secure Coding Practices Implemented:

    - Exception Handling: The application uses IllegalArgumentException to handle invalid arguments, which is a good practice for providing meaningful feedback.
    - Stateless Controllers: The controller is stateless, preventing issues that arise from maintaining state across requests.
    - Business Logic Delegation: Business logic is properly delegated to the service layer, keeping the controller focused on HTTP handling.

3. Best Practices Followed in Spring Boot Development:

    - Dependency Injection: Dependency injection via Spring's @Autowired is used for injecting ProductService into the ProductController.
    - Annotation-Based Configuration: Use of @Controller, @RequestMapping, @GetMapping, and @PostMapping for defining RESTful endpoints is a common and recommended practice.
    - Layered Architecture: Clear separation of concerns among controllers, services, repositories, and models, which promotes maintainability and testability.
    Lombok Usage: Utilization of Lombok annotations (@Getter and @Setter) to reduce boilerplate code in the Product model class.

### Reflection 2
#### 1. Unit Test
After implementing the unit tests for my Spring Boot application, I feel more confident in the robustness of my code. 

To ensure our unit tests are comprehensive enough to verify our program, we focus on the following strategies:

- Code Coverage: Utilizing tools like JaCoCo to measure the percentage of code covered by tests. Aiming for high coverage, such as 80% or above, is generally considered good practice 4.
- Edge Cases: Including tests for edge cases and unexpected inputs to ensure robustness against potential issues 4.
- Readability and Maintenance: Keeping tests simple and readable, using patterns like Arrange-Act-Assert, and avoiding logic in tests to make them maintainable and understandable

100% code coverage may sound ideal, but it doesn't necessarily mean that the code is bug-free. High coverage can indicate that many lines of code have been executed during testing, but it doesn't guarantee that all possible combinations of inputs and states have been tested. 

#### 2. Functional Test

 I've identified some areas where we can improve code cleanliness and maintainability while ensuring that the new code doesn't compromise code quality:

1. Duplication of Setup Procedures:
    - It appears that the new test suite duplicates setup procedures and instance variables from the existing CreateProductFunctionalTest class. This could lead to code redundancy and increase maintenance efforts.
    - To address this, I suggest creating a base test class that encapsulates common setup procedures and variables. Both test suites can then extend this base class, reducing duplication and ensuring consistent setup across tests.

2. Hardcoded Test Data:
   - The use of hardcoded values like "Test Product" and quantity "10" limits the flexibility of our tests and may not cover various scenarios.
   - To enhance test coverage and flexibility, we should consider parameterizing test data or using data providers. This approach allows us to run tests with different inputs without modifying the test logic.

3. Lack of Abstraction:
   - Direct interactions with web elements in the test methods can make tests hard to read and maintain, particularly if the UI changes.
   - To address this issue, I will implement the Page Object Model (POM) pattern. By creating classes that represent pages or components of the web application, we can encapsulate interactions with web elements, making our tests more readable and maintainable.

4. Tightly Coupled Test and Implementation Details:
     - The test suite seems tightly coupled to specific implementation details of the web application, such as element IDs and HTML structure. This could make our tests fragile to UI changes.
     - To mitigate this risk, we should reduce our reliance on specific implementation details by using more stable selectors or relying on text that is less likely to change. Implementing the POM pattern can also help isolate UI changes to specific classes, making updates easier to manage.

By addressing these issues, we can enhance the cleanliness and maintainability of our new functional test suite, ensuring that it remains a valuable asset for verifying the functionality of our application.

Note : Latest html template is on the functional-test branch (Edit: html template already updated on master, module 2)

## Module 2
### Reflection 1
1. Switched to Constructor Injection

    Original Issue: I used @Autowired on fields in my service classes. This approach isn't ideal because it makes testing harder, allows dependencies to be changed after creation, and makes it less clear what dependencies a class needs.

    Solution: I changed to using constructor injection. This means I now pass dependencies through the constructor, making our code cleaner, easier to test, and ensuring dependencies can't be changed once set. This aligns with recommended practices and improves our code's design.

2. Updated Test Method Access Modifiers

    Original Issue: My test methods were marked as public, which is unnecessary with JUnit 5. JUnit 5 doesn't require test methods to be public, and it's better to use the most restrictive access level that makes sense.

    Solution: I removed the public modifier from all test methods. This change keeps our code simple and follows the principle of using the least restrictive access level needed. It doesn't affect how tests run but makes our code cleaner and follows modern Java practices.

3. Adding more unit test for the code coverage

### Refelction 2

The CI/CD workflows defined in my ci.yml and sonarcloud.yml files effectively fulfill Continuous Integration (CI) by automating the build and test processes on every push and pull request, alongside conducting code quality checks with SonarCloud. This ensures that every change is automatically integrated, tested, and analyzed for quality, aligning with CI principles of maintaining a stable, high-quality codebase. However, for Continuous Deployment (CD), the setup doesn't automatically deploy changes to production after passing all tests and quality checks. To achieve CD, adding steps for automatic deployment post-successful build and tests would be necessary, transitioning from just integrating and testing code to also deploying it seamlessly to live environments.


