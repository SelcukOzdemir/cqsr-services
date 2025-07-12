CQRS SERVICES
Spring Boot + MySQL + MongoDB + Kafka + Docker

Overview
This project demonstrates a CQRS (Command Query Responsibility Segregation) architecture using Spring Boot. The command side persists data to MySQL, while the query side serves data from MongoDB. Apache Kafka is used as the communication backbone between write and read sides, enabling eventual consistency. The environment is containerized using Docker Compose, and the APIs are documented and testable via Swagger UI.

Features
Create orders via command endpoints
Read orders via query endpoints
Event-driven communication with Apache Kafka
Write-side persistence using MySQL
Read-side projection using MongoDB
Separation of concerns between command and query
UUID-based order identifiers
Spring Boot modular service structure
Swagger UI API documentation
Docker Compose for infrastructure orchestration

Technologies Used
Java 17
Spring Boot 3
Spring Data JPA
Spring Data MongoDB
Apache Kafka
MySQL
MongoDB
Lombok
Springdoc OpenAPI (Swagger)
Docker and Docker Compose
Maven

Project Structure
POST /api/commands/orders Creates a new order (writes to MySQL)
GET /api/queries/orders/{id} Retrieves an order by ID (reads from MongoDB)

Getting Started

Prerequisites
Java 17 or higher
Maven
Docker and Docker Compose

Clone the repository
git clone https://github.com/your-username/cqrs-order-service.git
cd cqrs-order-service

Start Kafka, MySQL, and MongoDB using Docker Compose

docker-compose.yml example:

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

Request:
{
"customerName": "John Doe",
"amount": 2500
}

Get Order by ID
GET /api/queries/orders/{orderId}

Response:
{
"orderId": "f3a1e456-bc7a-487e-9f15-5c7c2d3decc9",
"customerName": "John Doe",
"amount": 2500,
"status": "CREATED",
"createdAt": "2025-07-12T13:47:41.000Z"
}

Testing via Swagger UI
Access Swagger UI to explore and test endpoints:
http://localhost:8080/swagger-ui.html

Secure and stateless architecture with full command/query separation
Kafka ensures eventual consistency between write and read models
MongoDB serves as a fast read model optimized for queries
MySQL serves as the source of truth for all command operations

References
Spring Boot
https://spring.io/projects/spring-boot
Apache Kafka
https://kafka.apache.org
Spring Data MongoDB
https://spring.io/projects/spring-data-mongodb
Springdoc OpenAPI
https://springdoc.org
Docker Compose
https://docs.docker.com/compose
CQRS Pattern
https://martinfowler.com/bliki/CQRS.html
