# Trevally Challenge

This project is part of the Trevally challenge and is currently under development. The goal is to create an application for processing CSV files and persisting the data into a database, while also allowing customization of the information to be extracted based on the columns and labels provided by the user.

## Under Construction

Currently, the project is in the initial development phase. We are setting up the development environment with Docker Compose and Flyway for database management.

## Objective

The main objective of this project is to create a contact list from CSV files, enabling the user to choose the information to be extracted based on the columns and labels provided. This will provide greater flexibility in processing different types of CSV files.

## Services

### 1. Header Extraction Service

This is the first service developed, responsible for extracting the headers from a CSV file. The headers are returned as a list of DTO objects for later use in the application.

## Testing

To test the first endpoint, a Postman collection is available in the project. Before using this version, make sure to create the 'trevally_challenge' database.

## Next Steps

- Implement the data persistence service.
- Develop user interfaces for interaction with the system.
- Add more CSV file processing functionalities.
- Test and refine the application to ensure stability and performance.

Stay tuned for more updates on the project's progress!

