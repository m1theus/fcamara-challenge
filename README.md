# fcamara-challenge


## Introduction

This web application is built in Java and Spring.

## **API**

The API is available at `http://localhost:8080` so you can use by the following cURL.

List all purchases sorted by ASC.

```bash
curl --location 'localhost:8080/purchases' \
--header 'content-type: application/json' \
--header 'Accept: application/json'
```

List the biggest purchase.

```bash
curl --location 'localhost:8080/purchases/bigger-purchase/2020' \
--header 'content-type: application/json' \
--header 'Accept: application/json'
```

List the top 3 purchase by customers

```bash
curl --location 'localhost:8080/customers/loyalty' \
--header 'content-type: application/json' \
--header 'Accept: application/json'
```

Recommend a wine based on recurring purchase by a customer

```bash
curl --location 'localhost:8080/customers/27737287426/recommendation' \
--header 'content-type: application/json' \
--header 'Accept: application/json'
```

## **Usage**

To run the application, follow these steps:

1. Clone the repository:

   ```bash
   git clone git@github.com:m1theus/fcamara-challenge.git
   ```

2. Navigate to the project directory:

   ```bash
   cd fcamara-challenge
   ```

3. Run the application:

   ```bash
   ./gradlew bootRun
   ```
