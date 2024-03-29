## Challenge

This project is part of the a challenge and is currently under development. The goal is to create an application for processing CSV files and persisting the data into a database, while also allowing customization of the information to be extracted based on the columns and labels provided by the user.


## Objective

The main objective of this project is to create a contact list from CSV files, enabling the user to choose the information to be extracted based on the columns and labels provided. This will provide greater flexibility in processing different types of CSV files.

## Architecture

The project follows a layered architecture model, where each layer has specific responsibilities:

1. **Presentation Layer**:
   - Contains REST controllers handling HTTP requests related to CSV file processing.
   - Receives requests, processes them, and sends responses back to clients.

2. **Service Layer**:
   - Implements the business logic of the application, such as CSV header extraction and file processing.
   - Ensures that business rules are correctly applied.

3. **Persistence Layer**:
   - Not fully implemented in the current version of the project.
   - Will be responsible for accessing and manipulating persistent data in the MongoDB database.

4. **Infrastructure Layer**:
   - Provides technical support to the application, such as custom exceptions and error message configuration.
   - Defines general technical configurations, such as logging and integration with external services.

5. **Testing**:
   - Tests are written to validate the functionality and expected behavior of the application.
   - Includes unit and integration tests, ensuring code quality and proper system operation.

This architecture allows for clear separation of concerns, facilitating maintenance, testing, and evolution of the project. Each layer has its defined function, promoting modularity and scalability of the application.

## Technologies Used

- **Java**: The primary programming language used for backend development.
- **Spring Boot**: Provides a robust framework for building RESTful APIs and handling dependency injection.
- **JUnit 5**: Used for writing and running unit tests.
- **Mockito**: A mocking framework for creating mock objects in unit tests.
- **MongoDB**: A NoSQL database used for persisting data.
- **Postman**: Used for testing API endpoints and creating collections for automated testing.

## Services

### 1. Header Extraction Service

This is the first service developed, responsible for extracting the headers from a CSV file. The headers are returned as a list of DTO objects for later use in the application.

### 2. File Processing Service

The File Processing Service is responsible for processing CSV files. It allows customization of the extracted information based on the headers extracted from the file. Users can also rearrange information by specifying mapped columns.

## Running the Application

To run the Spring Boot application locally, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/fernandoericofilho/trevally_challenge/tree/master

   cd trevally-challenge

   mvn clean install

   mvn spring-boot:run

## Testing

To test the first endpoint, a Postman collection is available in the project. Before using this version, make sure to create the 'trevally_challenge' database.

### Using the Postman Collection

1. Import the provided Postman collection (`Trevally_Challenge.postman_collection.json`) into your Postman application.
2. Also, import the environment file (`trevally_env.json`) in Postman.
3. The collection contains three requests:
    - **Extract Headers**: Use this request to extract headers from a CSV file.
    - **Process File (Manual)**: Use this request to manually modify the result of header extraction and process the CSV file.
    - **Process File (Automatic)**: This request automatically processes the CSV file using the headers extracted from the previous request.

## Controllers

The application includes the following controllers:

- **CSVController**: Processes CSV files, extracts headers, and persists data.

- **SourceController**: Handles requests related to source, such as listing all sources, reading, and deleting.

These controllers are designed intentionally to provide restricted privileges on objects. For example, the `CSVController` extracts headers and persists data, while the `SourceController` lists all sources, gets, and deletes them.


## Next Steps

- Integrate Docker to run MongoDB to support data storage.
