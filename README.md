# tutorial-1-adpro

## Reflection 1

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
