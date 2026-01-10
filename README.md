Yapily Open Banking Middleware

A high-throughput middleware gateway built with Java 21 and Spring Boot 3.2. This service provides a resilient interface for the Yapily API, featuring asynchronous payment registration, event-driven status updates, and fintech-grade security.
Architecture & Key Features

    Virtual Thread Concurrency: Leverages Project Loom to handle thousands of concurrent requests with minimal resource overhead.

    Token Bucket Rate Limiting: Custom implementation using ArrayBlockingQueue and a background refiller to manage outbound traffic to the Yapily API.

    Event-Driven Design: Asynchronous payment processing via Kafka to decouple registration from processing.

    Secure Secret Management: Integrated with HashiCorp Vault for sensitive API credentials.

    Observability: Integrated Kafka-UI for real-time message monitoring and detailed Log4j2 asynchronous logging.

Infrastructure Setup

This project uses Docker to provide a standardized development environment.
1. Requirements

    JDK 21 (Temurin or OpenJDK recommended)

    Docker & Docker Compose

    Gradle 8.7 (Note: A specific version is required for Java 21 bytecode compatibility)

2. Launch Services

Run the following command to spin up Kafka (KRaft mode), Vault, and the Kafka UI:
Bash

docker compose up -d

3. Service Dashboard Access
Service	URL	Credentials
Kafka UI	http://localhost:8080	N/A
Vault UI	http://localhost:8200	Token: root-token
Configuration (Vault)

Before starting the application, you must configure your Yapily credentials in Vault:

    Access the Vault UI and log in with the root-token.

    Enable the kv-v2 secrets engine at path secret/.

    Create a secret at secret/yapily-middleware with:

        yapily.application.id: Your Yapily App ID

        yapily.application.secret: Your Yapily Secret

Build & Development
Gradle Setup

If you encounter dependency resolution errors, ensure your wrapper is aligned with Java 21:
Bash

./gradlew wrapper --gradle-version 8.7

Running the App
Bash

./gradlew bootRun

Testing

The project uses JUnit 5 and AssertJ for testing.
Bash

./gradlew test

Rate Limiting Logic

The middleware implements a Token Bucket algorithm to stay within Yapily’s API quotas.

    Bucket Capacity: 50 tokens

    Refill Rate: 10 tokens/second

    Behavior: Requests arriving when the bucket is empty receive an immediate 429 Too Many Requests response, preventing downstream congestion.