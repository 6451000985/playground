# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

LearnHub Playground - Spring Boot 4.0.2 backend application with user management and role-based access control (RBAC). Uses Java 21, PostgreSQL, and Flyway for database migrations.

## Build & Run Commands

```bash
# Build
./mvnw clean install

# Run application (starts on port 8082)
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=PlaygoundApplicationTests

# Run a single test method
./mvnw test -Dtest=PlaygoundApplicationTests#contextLoads
```

## Database Setup

Start PostgreSQL via Docker Compose (from project root):
```bash
docker compose -f src/main/resources/compose.yaml up -d
```

Database connection: `localhost:5436`, database/user/password: `playgroundposgresql`

## Architecture

**Package Structure:** `com.learnhub.playgound`
- `user/domain/` - JPA entities
- `user/repository/` - Spring Data JPA repositories
- `user/service/` - Business logic services

**Database Schema (Flyway migrations in `src/main/resources/db/migration/`):**
- `users` / `user_profiles` - User management with extended profile data
- `roles` / `permissions` / `role_permissions` / `user_roles` - RBAC system with resource:action permission pattern (e.g., `course:create`)

**API Documentation:** SpringDoc OpenAPI available at `/swagger-ui.html` when running

## Key Configuration

- Server port: 8082
- JPA DDL: `none` (schema managed by Flyway)
- DevTools: Hot reload enabled
- Spring Security: Available in pom.xml but currently commented out
