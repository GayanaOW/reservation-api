# Seat Reservation Spring Boot API

An event-driven seat reservation microservice built with **Java 21**, **Spring Boot 3.x**, **MySQL 8.0**, and **Apache Kafka**. This project demonstrates reliable backend state management combined with asynchronous event streaming for real-time notifications.

---

## Tech Stack

* **Backend Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Language:** Java 21
* **Database:** MySQL 8.0
* **Messaging & Event Streaming:** Apache Kafka 3.x (Spring Kafka)
* **Containerization:** Docker & Docker Compose

---

## Architecture & Event Flow

1. **REST Request:** A client sends a POST request to reserve a specific seat (e.g., `/api/seats/A5/reserve?email=user@example.com`).
2. **Transactional State Update:** The service layer validates seat existence and availability, updates the database status within a transaction, and persists the changes via JPA/Hibernate.
3. **Event Publishing:** Upon successful reservation, a `SeatReservedEvent` payload is published to the `seat-reservations` Kafka topic.
4. **Asynchronous Consumption:** A background Kafka consumer listener (`ReservationEventListener`) picks up the message and processes the event downstream.

---

## Project Structure

```text
reservation-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/reservation_api/
│   │   │   ├── controller/      # REST API endpoints (SeatController)
│   │   │   ├── model/           # JPA Entities (Seat, events)
│   │   │   ├── repository/      # Spring Data JPA Repositories
│   │   │   └── service/         # Business logic & Kafka producers/consumers
│   │   └── resources/
│   │       └── application.properties # Database & Kafka connection configs
│   └── test/
├── docker-compose.yml       # Local MySQL and Kafka orchestration
└── run.txt                  # Quick onboarding reference commands# Seat Reservation Spring Boot API

An event-driven seat reservation microservice built with **Java 21**, **Spring Boot 3.x**, **MySQL 8.0**, and **Apache Kafka**. This project demonstrates reliable backend state management combined with asynchronous event streaming for real-time notifications.

---

## Tech Stack

* **Backend Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Language:** Java 21
* **Database:** MySQL 8.0
* **Messaging & Event Streaming:** Apache Kafka 3.x (Spring Kafka)
* **Containerization:** Docker & Docker Compose

---

## Architecture & Event Flow

1. **REST Request:** A client sends a POST request to reserve a specific seat (e.g., `/api/seats/A5/reserve?email=user@example.com`).
2. **Transactional State Update:** The service layer validates seat existence and availability, updates the database status within a transaction, and persists the changes via JPA/Hibernate.
3. **Event Publishing:** Upon successful reservation, a `SeatReservedEvent` payload is published to the `seat-reservations` Kafka topic.
4. **Asynchronous Consumption:** A background Kafka consumer listener (`ReservationEventListener`) picks up the message and processes the event downstream.

---

## Project Structure

```text
reservation-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/reservation_api/
│   │   │   ├── controller/      # REST API endpoints (SeatController)
│   │   │   ├── model/           # JPA Entities (Seat, events)
│   │   │   ├── repository/      # Spring Data JPA Repositories
│   │   │   └── service/         # Business logic & Kafka producers/consumers
│   │   └── resources/
│   │       └── application.properties # Database & Kafka connection configs
│   └── test/
├── docker-compose.yml       # Local MySQL and Kafka orchestration
└── run.txt                  # Quick onboarding reference commands
