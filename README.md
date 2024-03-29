## Challenge

This project is part of the a challenge and is currently under development. The goal is to create an application for processing CSV files and persisting the data into a database, while also allowing customization of the information to be extracted based on the columns and labels provided by the user.

## Under Construction

Currently, the project is in the initial development phase. 
## Objective

The main objective of this project is to create a contact list from CSV files, enabling the user to choose the information to be extracted based on the columns and labels provided. This will provide greater flexibility in processing different types of CSV files.

## Services

### 1. Header Extraction Service

This is the first service developed, responsible for extracting the headers from a CSV file. The headers are returned as a list of DTO objects for later use in the application.

### 2. File Processing Service

The File Processing Service is responsible for processing CSV files. It allows customization of the extracted information based on the headers extracted from the file. Users can also rearrange information by specifying mapped columns.

## Testing

To test the first endpoint, a Postman collection is available in the project. Before using this version, make sure to create the 'trevally_challenge' database.

### Using the Postman Collection

1. Import the provided Postman collection (`Trevally_Challenge.postman_collection.json`) into your Postman application.
2. Also, import the environment file (`trevally_env.json`) in Postman.
3. The collection contains three requests:
    - **Extract Headers**: Use this request to extract headers from a CSV file.
    - **Process File (Manual)**: Use this request to manually modify the result of header extraction and process the CSV file.
    - **Process File (Automatic)**: This request automatically processes the CSV file using the headers extracted from the previous request.

## Next Steps

- Integrate Docker to run MongoDB to support data storage.
- Implement new endpoints for operations between contacts and contact attributes, allowing CRUD operations and contact data manipulation.
