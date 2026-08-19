# JobApp Monolith

Spring Boot application for managing companies, jobs, and reviews. This project exposes REST APIs for a simple job-search and recruitment workflow and can be run locally with Maven or with the published Docker image.

## Docker image

Published Docker image:

- Docker Hub: `psj2002/jobappimage:latest`

Pull and run:

```bash
docker pull psj2002/jobappimage:latest
docker run -p 8080:8080 psj2002/jobappimage:latest
```

## Tech stack

- Java 17
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- H2 Database (default app DB)
- Spring Boot Actuator
- Lombok
- Maven
- Docker / Docker Compose

## Features

- Create, fetch, update, and delete companies
- Manage jobs with CRUD endpoints
- Add and manage company reviews
- Health and monitoring endpoints with Actuator
- H2 console for local database inspection

## Project structure

```text
jobapp/
├── src/
│   ├── main/
│   │   ├── java/com/info/jobapp/
│   │   │   ├── company/
│   │   │   ├── job/
│   │   │   ├── review/
│   │   │   └── JobappApplication.java
│   │   └── resources/
│   │       └── application.yaml
├── docker-compose.yml
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── README.md
├── HELP.md
└── target/
```

## Prerequisites

- Java 17+
- Maven
- Docker (optional)

## Run locally

### Option 1: Maven

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Then open:

```text
http://localhost:8080
```

### Option 2: Docker Compose

This repo contains a `docker-compose.yml` setup for supporting services such as PostgreSQL and pgAdmin.

```bash
docker-compose up -d
```

Note: the current Spring Boot application configuration is set to use the H2 in-memory database by default, while the compose file provisions PostgreSQL and pgAdmin for local infrastructure/testing.

## API endpoints

### Companies

- `GET /api/companies` - get all companies
- `POST /api/companies` - create a company
- `POST /api/companies/all` - create multiple companies
- `GET /api/companies/{id}` - get company by id
- `PUT /api/companies/{id}` - update company
- `DELETE /api/companies/{id}` - delete company

### Jobs

- `GET /api/jobs` - get all jobs
- `POST /api/jobs` - create a job
- `POST /api/jobs/all` - create multiple jobs
- `PUT /api/jobs/{jobId}` - update a job
- `DELETE /api/jobs/{jobId}` - delete a job

### Reviews

- `GET /api/companies/{companyId}/reviews` - get all reviews for a company
- `GET /api/companies/{companyId}/reviews/{reviewId}` - get single review
- `POST /api/companies/{companyId}/reviews` - create review
- `PUT /api/companies/{companyId}/reviews/{reviewId}` - update review
- `DELETE /api/companies/{companyId}/reviews/{reviewId}` - delete review

## Monitoring

Actuator is enabled for health checks and runtime info.

Example:

```bash
curl http://localhost:8080/actuator/health
```

## Database access

H2 console is enabled for local development:

```text
http://localhost:8080/h2-console
```

Default H2 settings:

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `root`
- Password: `root`

## Notes

- This project is a monolithic backend service.
- Logging can be adjusted dynamically using the Actuator `/loggers` endpoint.
- The Docker image for this project is available at `psj2002/jobappimage:latest`.

## License

This project is intended for learning and development purposes.
