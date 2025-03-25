# **Spring Boot Kafka Producer and Consumer with Docker**

This project demonstrates a robust implementation of a **Kafka Producer** and **Kafka Consumer** application using **Spring Boot**. The applications are containerized using **Docker** and orchestrated with **Docker Compose**. The project is designed to showcase how to produce and consume messages in a distributed system using **Apache Kafka**.

---

## **Overview**

The project consists of two Spring Boot microservices:
1. **Kafka Producer**: A RESTful service that sends messages to a Kafka topic.
2. **Kafka Consumer**: A service that listens to the Kafka topic and processes incoming messages.

Both services are deployed alongside **Apache Kafka** and **Zookeeper** using Docker Compose. The project is ideal for learning and demonstrating:
- How to integrate Spring Boot with Kafka.
- How to use Docker for microservice deployment.
- The basics of message-driven architecture.

---

## **Features**

### **Kafka Producer**
- Exposes a REST API endpoint (`/api/messages`) to send messages to a Kafka topic.
- Messages are serialized as `<String, byte[]>`:
  - **Key**: Serialized as a `String`.
  - **Value**: Serialized as a `byte[]`.
- Configurable Kafka topic and broker settings via `application.yml`.

### **Kafka Consumer**
- Listens to the Kafka topic and consumes messages in real-time.
- Logs the consumed messages, including the key and value.

### **Dockerized Deployment**
- All components (Kafka, Zookeeper, Producer, and Consumer) are containerized using Docker.
- A custom Docker network ensures seamless communication between services.

### **Spring Boot Actuator**
- Provides health checks and monitoring endpoints (e.g., `/actuator/health`).
- Exposes all Actuator endpoints for debugging and monitoring during development.

---

## **Technologies Used**

- **Spring Boot**: For building the producer and consumer applications.
- **Apache Kafka**: For message brokering and distributed messaging.
- **Docker**: For containerizing and orchestrating the services.
- **Zookeeper**: For managing Kafka's distributed system.
- **Maven**: For dependency management and building the project.

---

## **System Architecture**

The project consists of the following components:

1. **Kafka Producer**:
   - A Spring Boot application that produces messages to a Kafka topic.
   - Exposes a REST API for external clients to send messages.

2. **Kafka Consumer**:
   - A Spring Boot application that consumes messages from the Kafka topic.
   - Processes and logs the messages.

3. **Apache Kafka**:
   - A distributed message broker that handles message publishing and subscribing.

4. **Zookeeper**:
   - A service for managing Kafka's distributed system and maintaining configuration.

5. **Docker Compose**:
   - Orchestrates the deployment of all services in isolated containers.

---

## **Setup Instructions**

### **Prerequisites**
- **Docker** and **Docker Compose** installed on your system.
- **Java 17** or higher installed (for local development).

---

### **Steps to Run the Project**

1. **Clone the Repository**:
   Clone the repository to your local machine:
   ```bash
   git clone https://github.com/raju4789/springboot-kafka-docker.git
   cd springboot-kafka-docker
   ```

2. **Start the Services**:
   Use Docker Compose to start all services (Zookeeper, Kafka, Producer, and Consumer):
   ```bash
   docker-compose up --build
   ```

3. **Verify the Setup**:
   - Check the health of the Kafka Producer:
     ```bash
     curl http://localhost:4000/actuator/health
     ```
   - Check the health of the Kafka Consumer:
     ```bash
     curl http://localhost:8082/actuator/health
     ```

4. **Send a Message**:
   Use the Kafka Producer's REST API to send a message:
   ```bash
   curl --location --request POST 'http://localhost:4000/api/messages?key=testKey' \
   --header 'Content-Type: text/plain' \
   --data 'Hello, Kafka!'
   ```

5. **Check the Consumer Logs**:
   The Kafka Consumer will log the consumed message:
   ```
   Consumed message: Key = testKey, Value = Hello, Kafka!
   ```

---

## **Project Structure**

```
springboot-kafka-docker/
├── kafka-producer/         # Kafka Producer Spring Boot application
│   ├── src/                # Source code for the producer
│   ├── Dockerfile          # Dockerfile for the producer
│   └── application.yml     # Configuration for the producer
├── kafka-consumer/         # Kafka Consumer Spring Boot application
│   ├── src/                # Source code for the consumer
│   ├── Dockerfile          # Dockerfile for the consumer
│   └── application.yml     # Configuration for the consumer
├── docker-compose.yml      # Docker Compose file for orchestrating services
└── README.md               # Project documentation
```

---

## **Endpoints**

### **Kafka Producer**
- **POST /api/messages**
  - **Description**: Sends a message to the Kafka topic.
  - **Request Parameters**:
    - `key` (query parameter): The key for the message.
    - `message` (body): The message content.
  - **Example**:
    ```bash
    curl --location --request POST 'http://localhost:4000/api/messages?key=testKey' \
    --header 'Content-Type: text/plain' \
    --data 'Hello, Kafka!'
    ```

---

## **Configuration**

### **Kafka Producer (`application.yml`)**
```yaml
spring:
  application:
    name: kafka-producer
  kafka:
    bootstrap-servers: kafka:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.ByteArraySerializer
server:
  port: 8081
  address: 0.0.0.0
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### **Kafka Consumer (`application.yml`)**
```yaml
spring:
  application:
    name: kafka-consumer
  kafka:
    bootstrap-servers: kafka:9092
    consumer:
      group-id: kafka-demo-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.ByteArrayDeserializer
      auto-offset-reset: earliest
server:
  port: 8082
  address: 0.0.0.0
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

---

## **License**
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
