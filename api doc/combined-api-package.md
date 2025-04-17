# Insurance Application API - Documentation and Postman Collection

## Table of Contents
1. [API Documentation](#api-documentation)
2. [Postman Collection](#postman-collection)
3. [How to Import the Postman Collection](#how-to-import-the-postman-collection)

## API Documentation

### Base URL

```
http://localhost:8080/v1
```

### Authentication

*Note: This API does not currently implement authentication.*

### API Endpoints

#### Customer Management

##### Get All Customers
- **Endpoint:** `GET /customers`
- **Description:** Retrieves a list of all customers.
- **Success Response:** `200 OK` with array of customer objects

##### Get Customer by ID
- **Endpoint:** `GET /customers/{id}`
- **Description:** Retrieves a specific customer by their ID.
- **Parameters:**
  - `id` (path, required): Customer's unique identifier
- **Success Response:** `200 OK` with customer object
- **Error Response:** `404 Not Found` if customer doesn't exist

##### Create New Customer
- **Endpoint:** `POST /customers`
- **Description:** Creates a new customer record.
- **Request Body:**
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "555-123-4567",
    "address": "123 Main St, Anytown",
    "dateOfBirth": "1985-05-15",
    "createdAt": "2025-04-16T10:30:00"
  }
  ```
- **Success Response:** `201 Created` with created customer object

##### Update Customer
- **Endpoint:** `PUT /customers/{id}`
- **Description:** Updates an existing customer record.
- **Parameters:**
  - `id` (path, required): Customer's unique identifier
- **Request Body:** Customer object with updated fields
- **Success Response:** `200 OK` with updated customer object
- **Error Response:** `404 Not Found` if customer doesn't exist

##### Delete Customer
- **Endpoint:** `DELETE /customers/{id}`
- **Description:** Deletes a customer record.
- **Parameters:**
  - `id` (path, required): Customer's unique identifier
- **Success Response:** `204 No Content`
- **Error Response:** `404 Not Found` if customer doesn't exist

##### Get Customer Age
- **Endpoint:** `GET /customers/{id}/age`
- **Description:** Calculates the age of a customer based on their date of birth.
- **Parameters:**
  - `id` (path, required): Customer's unique identifier
- **Success Response:** `200 OK` with age as an integer
- **Error Response:** `404 Not Found` if customer doesn't exist

##### Get Customer by Email
- **Endpoint:** `GET /customers/email/{email}`
- **Description:** Finds a customer by their email address.
- **Parameters:**
  - `email` (path, required): Customer's email address
- **Success Response:** `200 OK` with customer object
- **Error Response:** `404 Not Found` if customer doesn't exist

#### Auto Policy Management

##### Get All Auto Policies
- **Endpoint:** `GET /autopolicies`
- **Description:** Retrieves a list of all auto policies.
- **Success Response:** `200 OK` with array of auto policy objects

##### Get Auto Policy by ID
- **Endpoint:** `GET /autopolicies/{id}`
- **Description:** Retrieves a specific auto policy by its ID.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Success Response:** `200 OK` with auto policy object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Create New Auto Policy
- **Endpoint:** `POST /autopolicies`
- **Description:** Creates a new auto insurance policy.
- **Request Body:**
  ```json
  {
    "customerId": 1,
    "startDate": "2025-04-16",
    "endDate": "2026-04-15",
    "basePremium": 1200.00,
    "premium": 0.00,
    "policyType": "AutoInsurance",
    "status": "Active",
    "vehicle": {
      "vehicleMake": "Honda",
      "vehicleModel": "Civic",
      "vehicleYear": 2023,
      "vehicleAccidents": 0
    }
  }
  ```
- **Success Response:** `201 Created` with created auto policy object

##### Update Auto Policy
- **Endpoint:** `PUT /autopolicies/{id}`
- **Description:** Updates an existing auto policy.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Request Body:** Auto policy object with updated fields
- **Success Response:** `200 OK` with updated auto policy object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Delete Auto Policy
- **Endpoint:** `DELETE /autopolicies/{id}`
- **Description:** Deletes an auto policy.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Success Response:** `204 No Content`
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Get Auto Policies by Customer ID
- **Endpoint:** `GET /autopolicies/customers/{customerId}`
- **Description:** Retrieves all auto policies for a specific customer.
- **Parameters:**
  - `customerId` (path, required): Customer's unique identifier
- **Success Response:** `200 OK` with array of auto policy objects
- **Error Response:** `204 No Content` if no policies exist for the customer

##### Calculate Auto Policy Premium
- **Endpoint:** `POST /autopolicies/calculate`
- **Description:** Calculates the premium for an auto policy based on various factors.
- **Request Body:**
  ```json
  {
    "policyId": 1,
    "customerId": 1,
    "vehicle": {
      "vehicleMake": "Honda",
      "vehicleModel": "Civic",
      "vehicleYear": 2023,
      "vehicleAccidents": 0
    }
  }
  ```
- **Success Response:** `200 OK` with auto policy object including calculated premium
- **Error Response:** `404 Not Found` if policy or customer doesn't exist

#### Home Policy Management

##### Get All Home Policies
- **Endpoint:** `GET /homepolicies`
- **Description:** Retrieves a list of all home policies.
- **Success Response:** `200 OK` with array of home policy objects

##### Get Home Policy by ID
- **Endpoint:** `GET /homepolicies/{id}`
- **Description:** Retrieves a specific home policy by its ID.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Success Response:** `200 OK` with home policy object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Create New Home Policy
- **Endpoint:** `POST /homepolicies`
- **Description:** Creates a new home insurance policy.
- **Request Body:**
  ```json
  {
    "customerId": 1,
    "startDate": "2025-04-16",
    "endDate": "2026-04-15",
    "basePremium": 800.00,
    "premium": 0.00,
    "policyType": "HomeInsurance",
    "status": "Active",
    "dwelling": {
      "dwellingType": "Single Family House",
      "heatingType": "Electric",
      "location": "Urban",
      "age": 15,
      "homeValue": 350000.00
    }
  }
  ```
- **Success Response:** `201 Created` with created home policy object

##### Update Home Policy
- **Endpoint:** `PUT /homepolicies/{id}`
- **Description:** Updates an existing home policy.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Request Body:** Home policy object with updated fields
- **Success Response:** `200 OK` with updated home policy object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Delete Home Policy
- **Endpoint:** `DELETE /homepolicies/{id}`
- **Description:** Deletes a home policy.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Success Response:** `204 No Content`
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Get Home Policies by Customer ID
- **Endpoint:** `GET /homepolicies/customers/{customerId}`
- **Description:** Retrieves all home policies for a specific customer.
- **Parameters:**
  - `customerId` (path, required): Customer's unique identifier
- **Success Response:** `200 OK` with array of home policy objects
- **Error Response:** `204 No Content` if no policies exist for the customer

##### Calculate Home Policy Premium
- **Endpoint:** `POST /homepolicies/calculate`
- **Description:** Calculates the premium for a home policy based on various factors.
- **Request Body:**
  ```json
  {
    "policyId": 1,
    "customerId": 1,
    "dwelling": {
      "dwellingType": "Single Family House",
      "heatingType": "Electric",
      "location": "Urban",
      "age": 15,
      "homeValue": 350000.00
    }
  }
  ```
- **Success Response:** `200 OK` with home policy object including calculated premium
- **Error Response:** `404 Not Found` if policy or customer doesn't exist

#### Dwelling Management

##### Get Dwelling by Policy ID
- **Endpoint:** `GET /dwellings/policy/{policyId}`
- **Description:** Retrieves dwelling information for a specific home policy.
- **Parameters:**
  - `policyId` (path, required): Home policy's unique identifier
- **Success Response:** `200 OK` with dwelling object
- **Error Response:** `404 Not Found` if policy or dwelling doesn't exist

##### Update Dwelling for Policy
- **Endpoint:** `PUT /dwellings/policy/{policyId}`
- **Description:** Updates dwelling information for a specific home policy.
- **Parameters:**
  - `policyId` (path, required): Home policy's unique identifier
- **Request Body:**
  ```json
  {
    "dwellingType": "Single Family House",
    "heatingType": "Electric",
    "location": "Urban",
    "age": 15,
    "homeValue": 350000.00
  }
  ```
- **Success Response:** `200 OK` with updated dwelling object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Validate Dwelling
- **Endpoint:** `POST /dwellings/validate`
- **Description:** Validates dwelling information.
- **Request Body:**
  ```json
  {
    "dwellingType": "Single Family House",
    "heatingType": "Electric",
    "location": "Urban",
    "age": 15,
    "homeValue": 350000.00
  }
  ```
- **Success Response:** `200 OK` with validation results
  ```json
  {
    "valid": true,
    "errors": {}
  }
  ```

#### Vehicle Management

##### Get Vehicle by Policy ID
- **Endpoint:** `GET /vehicles/policy/{policyId}`
- **Description:** Retrieves vehicle information for a specific auto policy.
- **Parameters:**
  - `policyId` (path, required): Auto policy's unique identifier
- **Success Response:** `200 OK` with vehicle object
- **Error Response:** `404 Not Found` if policy or vehicle doesn't exist

##### Update Vehicle for Policy
- **Endpoint:** `PUT /vehicles/policy/{policyId}`
- **Description:** Updates vehicle information for a specific auto policy.
- **Parameters:**
  - `policyId` (path, required): Auto policy's unique identifier
- **Request Body:**
  ```json
  {
    "vehicleMake": "Honda",
    "vehicleModel": "Civic",
    "vehicleYear": 2023,
    "vehicleAccidents": 0
  }
  ```
- **Success Response:** `200 OK` with updated vehicle object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Validate Vehicle
- **Endpoint:** `POST /vehicles/validate`
- **Description:** Validates vehicle information.
- **Request Body:**
  ```json
  {
    "vehicleMake": "Honda",
    "vehicleModel": "Civic",
    "vehicleYear": 2023,
    "vehicleAccidents": 0
  }
  ```
- **Success Response:** `200 OK` with validation results
  ```json
  {
    "valid": true,
    "errors": {}
  }
  ```

#### Quote Management

##### Get All Quotes
- **Endpoint:** `GET /quotes`
- **Description:** Retrieves a list of all quotes.
- **Success Response:** `200 OK` with array of quote objects

##### Get Quote by ID
- **Endpoint:** `GET /quotes/{id}`
- **Description:** Retrieves a specific quote by its ID.
- **Parameters:**
  - `id` (path, required): Quote's unique identifier
- **Success Response:** `200 OK` with quote object
- **Error Response:** `404 Not Found` if quote doesn't exist

##### Create New Quote
- **Endpoint:** `POST /quotes`
- **Description:** Creates a new quote.
- **Request Body:**
  ```json
  {
    "customer": {
      "customerId": 1
    },
    "policyType": "AutoInsurance",
    "premium": 1200.00,
    "generatedDate": "2025-04-16"
  }
  ```
- **Success Response:** `201 Created` with created quote object

##### Update Quote
- **Endpoint:** `PUT /quotes/{id}`
- **Description:** Updates an existing quote.
- **Parameters:**
  - `id` (path, required): Quote's unique identifier
- **Request Body:** Quote object with updated fields
- **Success Response:** `200 OK` with updated quote object
- **Error Response:** `404 Not Found` if quote doesn't exist

##### Delete Quote
- **Endpoint:** `DELETE /quotes/{id}`
- **Description:** Deletes a quote.
- **Parameters:**
  - `id` (path, required): Quote's unique identifier
- **Success Response:** `204 No Content`
- **Error Response:** `404 Not Found` if quote doesn't exist

##### Get Quotes by Policy Type
- **Endpoint:** `GET /quotes/type/{policyType}`
- **Description:** Retrieves quotes by policy type.
- **Parameters:**
  - `policyType` (path, required): Type of policy (e.g., "AutoInsurance", "HomeInsurance")
- **Success Response:** `200 OK` with array of quote objects
- **Error Response:** `204 No Content` if no quotes exist for the policy type

##### Get Quotes After Date
- **Endpoint:** `GET /quotes/after?date={date}`
- **Description:** Retrieves quotes generated after a specific date.
- **Parameters:**
  - `date` (query, required): Date in format "yyyy-MM-dd"
- **Success Response:** `200 OK` with array of quote objects
- **Error Response:** `204 No Content` if no quotes exist after the date

##### Get Quotes by Customer ID
- **Endpoint:** `GET /quotes/customers/{customerId}`
- **Description:** Retrieves quotes for a specific customer.
- **Parameters:**
  - `customerId` (path, required): Customer's unique identifier
- **Success Response:** `200 OK` with array of quote objects
- **Error Response:** `204 No Content` if no quotes exist for the customer

#### User Management

##### Get All Users
- **Endpoint:** `GET /users`
- **Description:** Retrieves a list of all users.
- **Success Response:** `200 OK` with array of user objects

##### Get User by ID
- **Endpoint:** `GET /users/{id}`
- **Description:** Retrieves a specific user by their ID.
- **Parameters:**
  - `id` (path, required): User's unique identifier
- **Success Response:** `200 OK` with user object
- **Error Response:** `404 Not Found` if user doesn't exist

##### Create New User
- **Endpoint:** `POST /users`
- **Description:** Creates a new user account.
- **Request Body:**
  ```json
  {
    "customer": {
      "customerId": 1
    },
    "username": "johndoe",
    "passwordHash": "securePassword123",
    "role": "USER"
  }
  ```
- **Success Response:** `201 Created` with created user object
- **Error Response:** `409 Conflict` if username or email already exists

##### Update User
- **Endpoint:** `PUT /users/{id}`
- **Description:** Updates an existing user account.
- **Parameters:**
  - `id` (path, required): User's unique identifier
- **Request Body:** User object with updated fields
- **Success Response:** `200 OK` with updated user object
- **Error Response:** 
  - `404 Not Found` if user doesn't exist
  - `409 Conflict` if username or email already exists for a different user

##### Delete User
- **Endpoint:** `DELETE /users/{id}`
- **Description:** Deletes a user account.
- **Parameters:**
  - `id` (path, required): User's unique identifier
- **Success Response:** `204 No Content`
- **Error Response:** `404 Not Found` if user doesn't exist

##### Get Users by Role
- **Endpoint:** `GET /users/role/{role}`
- **Description:** Retrieves users by role.
- **Parameters:**
  - `role` (path, required): User role (e.g., "ADMIN", "USER")
- **Success Response:** `200 OK` with array of user objects

##### Check Username Exists
- **Endpoint:** `GET /users/check-username/{username}`
- **Description:** Checks if a username is already taken.
- **Parameters:**
  - `username` (path, required): Username to check
- **Success Response:** `200 OK` with boolean result

##### Check Email Exists
- **Endpoint:** `GET /users/check-email/{email}`
- **Description:** Checks if an email is already registered.
- **Parameters:**
  - `email` (path, required): Email to check
- **Success Response:** `200 OK` with boolean result

#### General Policy Management

##### Get All Policies
- **Endpoint:** `GET /policies`
- **Description:** Retrieves a list of all policies (auto and home).
- **Success Response:** `200 OK` with array of policy objects

##### Get Policy by ID
- **Endpoint:** `GET /policies/{id}`
- **Description:** Retrieves a specific policy by its ID.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Success Response:** `200 OK` with policy object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Create New Policy
- **Endpoint:** `POST /policies`
- **Description:** Creates a new generic policy (not recommended - use specific policy endpoints).
- **Request Body:**
  ```json
  {
    "customerId": 1,
    "startDate": "2025-04-16",
    "endDate": "2026-04-15",
    "basePremium": 1000.00,
    "premium": 1200.00,
    "policyType": "AutoInsurance",
    "status": "Active"
  }
  ```
- **Success Response:** `201 Created` with created policy object

##### Update Policy
- **Endpoint:** `PUT /policies/{id}`
- **Description:** Updates an existing policy.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Request Body:** Policy object with updated fields
- **Success Response:** `200 OK` with updated policy object
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Delete Policy
- **Endpoint:** `DELETE /policies/{id}`
- **Description:** Deletes a policy.
- **Parameters:**
  - `id` (path, required): Policy's unique identifier
- **Success Response:** `204 No Content`
- **Error Response:** `404 Not Found` if policy doesn't exist

##### Get Policies by Customer ID
- **Endpoint:** `GET /policies/customers/{customerId}`
- **Description:** Retrieves all policies for a specific customer.
- **Parameters:**
  - `customerId` (path, required): Customer's unique identifier
- **Success Response:** `200 OK` with array of policy objects
- **Error Response:** `204 No Content` if no policies exist for the customer

##### Get Policies by Type
- **Endpoint:** `GET /policies/type/{policyType}`
- **Description:** Retrieves policies by type.
- **Parameters:**
  - `policyType` (path, required): Type of policy (e.g., "AutoInsurance", "HomeInsurance")
- **Success Response:** `200 OK` with array of policy objects
- **Error Response:** `204 No Content` if no policies exist of the type

##### Get Policies by Status
- **Endpoint:** `GET /policies/status/{status}`
- **Description:** Retrieves policies by status.
- **Parameters:**
  - `status` (path, required): Policy status (e.g., "Active", "Cancelled", "Pending")
- **Success Response:** `200 OK` with array of policy objects
- **Error Response:** `204 No Content` if no policies exist with the status

### Error Responses

All endpoints may return the following error responses:

- `400 Bad Request`: Invalid request syntax, invalid request parameters, or missing required fields
- `404 Not Found`: Resource not found
- `409 Conflict`: Resource already exists (for creation operations)
- `500 Internal Server Error`: Something went wrong on the server

### Data Models

#### Customer
```json
{
  "customerId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "555-123-4567",
  "address": "123 Main St, Anytown",
  "dateOfBirth": "1985-05-15",
  "createdAt": "2025-04-16T10:30:00"
}
```

#### Policy (Base)
```json
{
  "policyId": 1,
  "customerId": 1,
  "startDate": "2025-04-16",
  "endDate": "2026-04-15",
  "basePremium": 1000.00,
  "premium": 1200.00,
  "policyType": "AutoInsurance",
  "status": "Active"
}
```

#### Auto Policy
```json
{
  "policyId": 1,
  "customerId": 1,
  "startDate": "2025-04-16",
  "endDate": "2026-04-15",
  "basePremium": 1200.00,
  "premium": 1500.00,
  "policyType": "AutoInsurance",
  "status": "Active",
  "ageFactor": 1.0,
  "accidentFactor": 1.0,
  "vehicleFactor": 1.25,
  "vehicle": {
    "vehicleId": 1,
    "vehicleMake": "Honda",
    "vehicleModel": "Civic",
    "vehicleYear": 2023,
    "vehicleAccidents": 0
  }
}
```

#### Home Policy
```json
{
  "policyId": 1,
  "customerId": 1,
  "startDate": "2025-04-16",
  "endDate": "2026-04-15",
  "basePremium": 800.00,
  "premium": 1000.00,
  "policyType": "HomeInsurance",
  "status": "Active",
  "additional": 200.00,
  "ageFactor": 1.0,
  "locationFactor": 1.0,
  "heatingFactor": 1.25,
  "dwelling": {
    "dwellingId": 1,
    "dwellingType": "Single Family House",
    "heatingType": "Electric",
    "location": "Urban",
    "age": 15,
    "homeValue": 350000.00
  }
}
```

#### Vehicle
```json
{
  "vehicleId": 1,
  "vehicleMake": "Honda",
  "vehicleModel": "Civic",
  "vehicleYear": 2023,
  "vehicleAccidents": 0
}
```

#### Dwelling
```json
{
  "dwellingId": 1,
  "dwellingType": "Single Family House",
  "heatingType": "Electric",
  "location": "Urban",
  "age": 15,
  "homeValue": 350000.00
}
```

#### Quote
```json
{
  "quoteId": 1,
  "customer": {
    "customerId": 1,
    "firstName": "John",
    "lastName": "Doe"
  },
  "policyType": "AutoInsurance",
  "premium": 1200.00,
  "generatedDate": "2025-04-16"
}
```

#### User
```json
{
  "userId": 1,
  "customer": {
    "customerId": 1,
    "firstName": "John",
    "lastName": "Doe"
  },
  "username": "johndoe",
  "passwordHash": "[hashed password]",
  "role": "USER"
}
```

## Postman Collection

Below is a Postman collection in JSON format. Copy this entire JSON object and paste on Postman's import or save it as a file (JSON/txt) or import it directly into Postman.

```json
{
    "info": {
      "_postman_id": "a1b2c3d4-e5f6-7890-a1b2-c3d4e5f67890",
      "name": "Insurance API Collection",
      "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
    },
    "item": [
      {
        "name": "Users",
        "item": [
          {
            "name": "Get All Users",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/users",
                "host": ["{{base_url}}"],
                "path": ["v1", "users"]
              }
            },
            "response": []
          },
          {
            "name": "Get User by ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/users/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "users", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Create User",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"username\": \"johndoe\",\n    \"email\": \"john@example.com\",\n    \"passwordHash\": \"hashedpassword\",\n    \"role\": \"customer\"\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/users",
                "host": ["{{base_url}}"],
                "path": ["v1", "users"]
              }
            },
            "response": []
          },
          {
            "name": "Update User",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"username\": \"updatedjohn\",\n    \"email\": \"updated@example.com\",\n    \"passwordHash\": \"newhashedpassword\",\n    \"role\": \"customer\"\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/users/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "users", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Delete User",
            "request": {
              "method": "DELETE",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/users/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "users", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Get Users by Role",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/users/role/customer",
                "host": ["{{base_url}}"],
                "path": ["v1", "users", "role", "customer"]
              }
            },
            "response": []
          },
          {
            "name": "Check Username Exists",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/users/check-username/johndoe",
                "host": ["{{base_url}}"],
                "path": ["v1", "users", "check-username", "johndoe"]
              }
            },
            "response": []
          },
          {
            "name": "Check Email Exists",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/users/check-email/john@example.com",
                "host": ["{{base_url}}"],
                "path": ["v1", "users", "check-email", "john@example.com"]
              }
            },
            "response": []
          }
        ]
      },
      {
        "name": "Customers",
        "item": [
          {
            "name": "Get All Customers",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/customers",
                "host": ["{{base_url}}"],
                "path": ["v1", "customers"]
              }
            },
            "response": []
          },
          {
            "name": "Get Customer by ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/customers/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "customers", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Create Customer",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"firstName\": \"John\",\n    \"lastName\": \"Doe\",\n    \"email\": \"john@example.com\",\n    \"dateOfBirth\": \"1990-01-01\"\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/customers",
                "host": ["{{base_url}}"],
                "path": ["v1", "customers"]
              }
            },
            "response": []
          },
          {
            "name": "Update Customer",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"firstName\": \"Updated\",\n    \"lastName\": \"Name\",\n    \"email\": \"updated@example.com\",\n    \"dateOfBirth\": \"1990-01-01\"\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/customers/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "customers", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Delete Customer",
            "request": {
              "method": "DELETE",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/customers/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "customers", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Get Customer Age",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/customers/1/age",
                "host": ["{{base_url}}"],
                "path": ["v1", "customers", "1", "age"]
              }
            },
            "response": []
          },
          {
            "name": "Get Customer by Email",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/customers/email/john@example.com",
                "host": ["{{base_url}}"],
                "path": ["v1", "customers", "email", "john@example.com"]
              }
            },
            "response": []
          }
        ]
      },
      {
        "name": "Policies",
        "item": [
          {
            "name": "Get All Policies",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/policies",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies"]
              }
            },
            "response": []
          },
          {
            "name": "Get Policy by ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/policies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Create Policy",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"AUTO\",\n    \"status\": \"ACTIVE\",\n    \"customerId\": 1\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/policies",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies"]
              }
            },
            "response": []
          },
          {
            "name": "Update Policy",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"AUTO\",\n    \"status\": \"INACTIVE\",\n    \"customerId\": 1\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/policies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Delete Policy",
            "request": {
              "method": "DELETE",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/policies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Get Policies by Customer ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/policies/customer/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies", "customer", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Get Policies by Type",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/policies/type/AUTO",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies", "type", "AUTO"]
              }
            },
            "response": []
          },
          {
            "name": "Get Policies by Status",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/policies/status/ACTIVE",
                "host": ["{{base_url}}"],
                "path": ["v1", "policies", "status", "ACTIVE"]
              }
            },
            "response": []
          }
        ]
      },
      {
        "name": "Auto Policies",
        "item": [
          {
            "name": "Get All Auto Policies",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/autopolicies",
                "host": ["{{base_url}}"],
                "path": ["v1", "autopolicies"]
              }
            },
            "response": []
          },
          {
            "name": "Get Auto Policy by ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/autopolicies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "autopolicies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Create Auto Policy",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"AUTO\",\n    \"status\": \"ACTIVE\",\n    \"customerId\": 1,\n    \"vehicle\": {\n        \"vehicleMake\": \"Toyota\",\n        \"vehicleModel\": \"Camry\",\n        \"vehicleYear\": 2020,\n        \"vehicleAccidents\": 0\n    }\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/autopolicies",
                "host": ["{{base_url}}"],
                "path": ["v1", "autopolicies"]
              }
            },
            "response": []
          },
          {
            "name": "Update Auto Policy",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"AUTO\",\n    \"status\": \"ACTIVE\",\n    \"customerId\": 1,\n    \"vehicle\": {\n        \"vehicleMake\": \"Toyota\",\n        \"vehicleModel\": \"Camry\",\n        \"vehicleYear\": 2020,\n        \"vehicleAccidents\": 1\n    }\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/autopolicies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "autopolicies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Delete Auto Policy",
            "request": {
              "method": "DELETE",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/autopolicies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "autopolicies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Get Auto Policies by Customer ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/autopolicies/customer/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "autopolicies", "customer", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Calculate Auto Policy Premium",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyId\": 1,\n    \"customerId\": 1,\n    \"vehicle\": {\n        \"vehicleMake\": \"Toyota\",\n        \"vehicleModel\": \"Camry\",\n        \"vehicleYear\": 2020,\n        \"vehicleAccidents\": 0\n    }\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/autopolicies/calculate",
                "host": ["{{base_url}}"],
                "path": ["v1", "autopolicies", "calculate"]
              }
            },
            "response": []
          }
        ]
      },
      {
        "name": "Vehicles",
        "item": [
          {
            "name": "Get Vehicle by Policy ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/vehicles/policy/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "vehicles", "policy", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Update Vehicle for Policy",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"vehicleMake\": \"Honda\",\n    \"vehicleModel\": \"Accord\",\n    \"vehicleYear\": 2021,\n    \"vehicleAccidents\": 0\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/vehicles/policy/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "vehicles", "policy", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Validate Vehicle",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"vehicleMake\": \"Toyota\",\n    \"vehicleModel\": \"\",\n    \"vehicleYear\": 1899,\n    \"vehicleAccidents\": -1\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/vehicles/validate",
                "host": ["{{base_url}}"],
                "path": ["v1", "vehicles", "validate"]
              }
            },
            "response": []
          }
        ]
      },
      {
        "name": "Home Policies",
        "item": [
          {
            "name": "Get All Home Policies",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/homepolicies",
                "host": ["{{base_url}}"],
                "path": ["v1", "homepolicies"]
              }
            },
            "response": []
          },
          {
            "name": "Get Home Policy by ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/homepolicies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "homepolicies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Create Home Policy",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"HOME\",\n    \"status\": \"ACTIVE\",\n    \"customerId\": 1,\n    \"dwelling\": {\n        \"dwellingType\": \"HOUSE\",\n        \"heatingType\": \"GAS\",\n        \"location\": \"New York\",\n        \"age\": 10,\n        \"homeValue\": 500000\n    }\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/homepolicies",
                "host": ["{{base_url}}"],
                "path": ["v1", "homepolicies"]
              }
            },
            "response": []
          },
          {
            "name": "Update Home Policy",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"HOME\",\n    \"status\": \"ACTIVE\",\n    \"customerId\": 1,\n    \"dwelling\": {\n        \"dwellingType\": \"HOUSE\",\n        \"heatingType\": \"GAS\",\n        \"location\": \"New York\",\n        \"age\": 10,\n        \"homeValue\": 550000\n    }\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/homepolicies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "homepolicies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Delete Home Policy",
            "request": {
              "method": "DELETE",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/homepolicies/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "homepolicies", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Get Home Policies by Customer ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/homepolicies/customer/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "homepolicies", "customer", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Calculate Home Policy Premium",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyId\": 1,\n    \"customerId\": 1,\n    \"dwelling\": {\n        \"dwellingType\": \"HOUSE\",\n        \"heatingType\": \"GAS\",\n        \"location\": \"New York\",\n        \"age\": 10,\n        \"homeValue\": 500000\n    }\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/homepolicies/calculate",
                "host": ["{{base_url}}"],
                "path": ["v1", "homepolicies", "calculate"]
              }
            },
            "response": []
          }
        ]
      },
      {
        "name": "Dwellings",
        "item": [
          {
            "name": "Get Dwelling by Policy ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/dwellings/policy/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "dwellings", "policy", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Update Dwelling for Policy",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"dwellingType\": \"APARTMENT\",\n    \"heatingType\": \"ELECTRIC\",\n    \"location\": \"Boston\",\n    \"age\": 5,\n    \"homeValue\": 350000\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/dwellings/policy/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "dwellings", "policy", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Validate Dwelling",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"dwellingType\": \"\",\n    \"heatingType\": \"\",\n    \"location\": \"\",\n    \"age\": -1,\n    \"homeValue\": 0\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/dwellings/validate",
                "host": ["{{base_url}}"],
                "path": ["v1", "dwellings", "validate"]
              }
            },
            "response": []
          }
        ]
      },
      {
        "name": "Quotes",
        "item": [
          {
            "name": "Get All Quotes",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/quotes",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes"]
              }
            },
            "response": []
          },
          {
            "name": "Get Quote by ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/quotes/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Create Quote",
            "request": {
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"AUTO\",\n    \"customerId\": 1,\n    \"generatedDate\": \"2023-01-01\",\n    \"expirationDate\": \"2023-02-01\",\n    \"premium\": 500.00\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/quotes",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes"]
              }
            },
            "response": []
          },
          {
            "name": "Update Quote",
            "request": {
              "method": "PUT",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n    \"policyType\": \"AUTO\",\n    \"customerId\": 1,\n    \"generatedDate\": \"2023-01-01\",\n    \"expirationDate\": \"2023-02-01\",\n    \"premium\": 550.00\n}"
              },
              "url": {
                "raw": "{{base_url}}/v1/quotes/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Delete Quote",
            "request": {
              "method": "DELETE",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/quotes/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes", "1"]
              }
            },
            "response": []
          },
          {
            "name": "Get Quotes by Policy Type",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/quotes/type/AUTO",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes", "type", "AUTO"]
              }
            },
            "response": []
          },
          {
            "name": "Get Quotes After Date",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/quotes/after?date=2023-01-01",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes", "after"],
                "query": [
                  {
                    "key": "date",
                    "value": "2023-01-01"
                  }
                ]
              }
            },
            "response": []
          },
          {
            "name": "Get Quotes by Customer ID",
            "request": {
              "method": "GET",
              "header": [],
              "url": {
                "raw": "{{base_url}}/v1/quotes/customer/1",
                "host": ["{{base_url}}"],
                "path": ["v1", "quotes", "customer", "1"]
              }
            },
            "response": []
          }
        ]
      }
    ],
    "variable": [
      {
        "key": "base_url",
        "value": "http://localhost:8080"
      }
    ]
  }