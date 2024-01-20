# Spring Boot Template

This is a template for a Spring Boot project.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 1.8
- Maven

### Installing

A step by step series of examples that tell you how to get a development environment running.

1. Clone the repository
```sh
git clone <repository-url>
```

2. Navigate to the project directory
```sh
cd spring-boot-template
```

3.Build the project
```sh
mvn clean package
```

4. Run the project
```sh
java -jar target/spring-boot-template-0.0.1-SNAPSHOT.jar
```

### Running the tests

Explain how to run the automated tests for this system.
```sh
mvn test
```

Integration tests TBD  
Selenium tests TBD

### Docker

Build docker image
```sh
./scripts/build-docker.cmd
```

Start docker container
```sh
./scripts/start-docker.cmd
```

### Deployment

#### Using Fabric

Edit the `.env` file to set the environment variables.

```sh
mv .env.example .env
```

Install the python dependencies (recommended to use a virtual environment).

```sh
pip install -r requirements.txt
```

Deploy the application.

```sh
fab deploy
```

### Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management

### Authors

* **[Luan Vu]

### License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

### Tasks

- [ ] Generate api docs
- [ ] Add Unit Test
- [ ] Add Integration Test
- [ ] Add Performance Test
- [ ] Add Code Style
- [ ] Add Code Formatter
- [ ] Use lombok
- [ ] Add *unix scripts
