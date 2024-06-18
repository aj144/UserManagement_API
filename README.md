
## User Management REST API using Spring Boot 
This project implements a User Management REST API with Spring Boot, utilizing Spring Security for authentication and Spring Data JPA for database operations. The API includes endpoints for user registration and fetching user details securely.
## Setup Instructions
###  Prerequisites
- Java 11 or higher
- Maven 3.6.x or higher
- Eclipse IDE (or any preferred IDE)
- Postman (for testing API endpoints)
## Steps to Run the Application
### 1.Clone the Repository
git clone https://github.com/your-username/user-management-api.git
cd user-management-api
OR
 - Downlode the zip file and unzip
###  2.Import the project in eclipse ide
1. **Open Eclipse IDE.**
   
2. **Select File > Import > Existing Maven Projects.**

3. **Browse to the cloned repository directory and select the pom.xml file.**

4. **Click Finish to import the project.**

### 3.Configure Database
- Configure your database connection details (application.properties or application.yml) in the src/main/resources directory.


In Markdown, this will display as:


### Example application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
  ```

### Run the Application
1. **Build and run the application using Maven:**

   ```bash
   mvn spring-boot:run
 2.  Alternatively, you can run the application directly from your IDE by running the main class (`UserManagementApiApplication.java`).


   
### Testing APIs with Postman
**Open Postman.**

**Register User API:**  
Send a POST request to `http://localhost:8181/api/user/register` with the following JSON body:
```
{
  "username": "john_doe",
  "email": "john.doe@example.com",
  "password": "securepassword"
}

```
- Response: Returns HTTP status 200 OK upon successful registration.## Fetch User

- **Endpoint:** `GET /api/user/fetch`
  
- **Description:** Fetch user details by username.
  
- **Authorization:** Basic Authentication required (username: user, password: 1234).
  
- **Request Parameters:**
  - `username`: Username of the user to fetch.
  
- **Response:**

  - **Success Response:**
  
    ```json
    {
      "username": "john_doe",
      "email": "john.doe@example.com",
      "firstName": "John",
      "lastName": "Doe",
      "createdAt": "2024-06-18T12:00:00Z"
    }
    ```
  
  - **Error Response:**
  
    - **Status Code:** 404 Not Found
    - **Content:**
    
      ```json
      {
        "error": "User not found"
      }
      ```

### Explanation:

- The endpoint `GET /api/user/fetch` is used to retrieve user details based on the provided username.
- Basic Authentication is required with the credentials `user` and `1234`.
- **Request Parameters:** `username` is a mandatory parameter that specifies the username of the user whose details are to be fetched.
- **Success Response:** Returns a JSON object containing the user details including `username`, `email`, `firstName`, `lastName`, and `createdAt` (creation date).
- **Error Response:** If the user with the specified username is not found, the API returns a `404 Not Found` status with an error message `{"error": "User not found"}`.

This updated format clearly specifies how the API behaves in both success and error scenarios, making it easier for developers to understand and handle different outcomes when using your API. Adjust the error message and status code according to your application's conventions and requirements.
## Exception Handling

The application implements robust exception handling to ensure proper error responses for various scenarios:

- **Invalid requests (400 Bad Request):** Occurs when the request syntax is incorrect or parameters are invalid.
- **Resource not found (404 Not Found):** Returned when attempting to access a resource that does not exist.
- **Unauthorized access (401 Unauthorized):** Indicates that the request lacks valid authentication credentials.
- **Internal server errors (500 Internal Server Error):** Occurs when an unexpected condition prevents the server from fulfilling the request.

### Error Response Format

All error responses follow a standardized JSON format to provide clear information about the error:

```json
{
  "timestamp": "2024-06-18T14:30:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with username 'john_doe'",
  "path": "/api/user/fetch"
}
