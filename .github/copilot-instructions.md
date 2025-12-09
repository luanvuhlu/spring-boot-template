# GitHub Copilot Instructions

## General Preferences

- Use Java with latest version as the default programming language.
- Follow the Google Java Style Guide for coding conventions.
- Use 4 spaces for indentation.
- Prefer double quotes for strings.
- Provide a maximum of 3 suggestions at a time.

## Code Comments

- Include detailed comments for complex logic and algorithms.
- Use Javadoc comments for methods and classes.
- Add TODO comments for incomplete or future enhancements.

## Code Structure

- Organize code into logical packages.
- Use meaningful and descriptive names for classes, methods, and variables.
- Ensure that methods are small and focused on a single task (Single Responsibility Principle).

## Testing

- Suggest writing unit tests using JUnit 5.
- Encourage the use of mock objects for unit testing.
- Include example test cases for each public method.

## Performance and Optimization

- Optimize code for readability over performance unless specified otherwise.
- Suggest using efficient algorithms and data structures.
- Avoid premature optimization; focus on clean and maintainable code first.

## Error Handling

- Use checked exceptions for recoverable errors and unchecked exceptions for programming errors.
- Provide meaningful error messages and log exceptions appropriately.
- Suggest creating custom exception classes for domain-specific errors.

## Best Practices

- Encourage the use of design patterns where applicable (e.g., Singleton, Factory, Observer).
- Suggest refactoring opportunities to improve code quality.
- Promote the use of version control best practices (e.g., meaningful commit messages, small and
  focused commits).

## Documentation

- Ensure that all public APIs are well-documented.
- Suggest creating a README.md file with project overview, setup instructions, and usage examples.
- Encourage the use of inline comments for complex code sections.

## Security

- Highlight potential security vulnerabilities and suggest mitigation strategies.
- Encourage input validation and sanitation to prevent common security issues (e.g., SQL injection,
  XSS).
- Suggest using libraries and frameworks with active security support and updates.
