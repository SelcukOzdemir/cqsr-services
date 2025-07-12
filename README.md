CQRS SERVICES
Spring Boot + MySQL + MongoDB + Kafka + Docker

Overview

This project implements a microservice-based backend system using the CQRS (Command Query Responsibility Segregation) pattern. It features separate models for write (command) and read (query) operations. The write side persists data into MySQL and publishes events through Apache Kafka. The read side listens to these events and stores the read model in MongoDB for optimized query performance. Docker Compose is used to simplify local development and infrastructure setup.

Features

Separation of write (commands) and read (queries)
MySQL as the write-side persistent store
MongoDB as the read-side optimized view
Apache Kafka for asynchronous event propagation
Event-driven communication between services
UUID-based order identity management
Spring Boot modular architecture
Swagger UI for API documentation and testing
Docker Compose for infrastructure orchestration

Technologies Used

Java 17
Spring Boot 3
Spring Data JPA
Spring Data MongoDB
Apache Kafka
MySQL
MongoDB
Springdoc OpenAPI (Swagger)
Lombok
Docker and Docker Compose
Maven

Project Structure

POST /api/commands/orders - Creates a new order (writes to MySQL)
GET /api/queries/orders/{id} - Retrieves an order by ID (reads from MongoDB)

Getting Started


Prerequisites

Java 17 or higher
Maven
Docker and Docker Compose

Clone the repository
git clone https://github.com/your-username/cqrs-order-service.git
cd cqrs-order-service

Set up infrastructure with Docker Compose

docker-compose.yml:

services:
mysql:
image: mysql:8
environment:
MYSQL_ROOT_PASSWORD: root
MYSQL_DATABASE: cqrs_orders
ports:
- 3306:3306

mongodb:
image: mongo:6
ports:
- 27017:27017

zookeeper:
image: confluentinc/cp-zookeeper:latest
environment:
ZOOKEEPER_CLIENT_PORT: 2181
ports:
- 2181:2181

kafka:
image: confluentinc/cp-kafka:latest
ports:
- 9092:9092
environment:
KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

Run:
docker-compose up -d

Configure application.yml or application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/cqrs_orders
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=cqrs-read-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.properties.spring.json.trusted.packages=*

Run the application
mvn spring-boot:run

API Endpoints

Create Order
POST /api/commands/orders

Request body:
{
"customerName": "John Doe",
"amount": 5000
}

Get Order by ID
GET /api/queries/orders/{orderId}

Sample response:
{
"orderId": "b3c4d001-4e9e-4f84-bdc4-7f42614f8201",
"customerName": "John Doe",
"amount": 5000,
"status": "CREATED",
"createdAt": "2025-07-12T15:20:11.123Z"
}

How To Run And Test Application with Dockerfile (Optional)
docker build -t cqrs-order-service .
docker run -p 8080:8080 --network=host cqrs-order-service

How To Run And Test Application with docker-compose.yml (Optional)
docker-compose up --build
Ensure that MySQL, Kafka, MongoDB, and Zookeeper containers are running correctly.
