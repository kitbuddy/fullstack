# TECHNOLOGY.md

## Executive Summary
This repository is a small full‑stack sample: a Java Spring Boot backend (backend/) and an Angular frontend (client/demo/). The backend exposes a small REST API and fetches todo data from an external public API (https://jsonplaceholder.typicode.com). The frontend is an Angular SPA. This document maps components, lists build/run/test commands, and offers recommendations for CI, containerization, and developer setup.

---

## Languages & Frameworks
- Java (JDK 21) — configured in backend/pom.xml
- Spring Boot (parent POM v4.0.5)
- Maven (wrapper provided: backend/mvnw)
- Lombok (used in backend models)
- TypeScript (~5.5) and Angular (18.x) — client/demo
- Angular CLI (ng)
- HTML / SCSS for frontend assets
- Test frameworks:
  - Backend: Spring Boot Test (JUnit)
  - Frontend: Vitest / Angular test configuration

---

## Architecture Overview
Nodes:
- Frontend: Angular SPA (client/demo) — entry: client/demo/src/main.ts
- Backend: Spring Boot REST API (backend) — entry: backend/src/main/java/com/fullstack/demo/DemoApplication.java
- External API: JSONPlaceholder (https://jsonplaceholder.typicode.com)

Data flow (typical): Browser -> Backend (/api/todos) -> External API

Recommended diagram nodes: Browser (Angular) | Backend (Spring Boot) | External API | Build tools (Maven, npm)

---

## Component Mapping (files -> component)
- Backend (backend/)
  - backend/pom.xml — Maven project definition
  - backend/mvnw — Maven wrapper
  - Entrypoint: backend/src/main/java/com/fullstack/demo/DemoApplication.java
  - Controller: backend/src/main/java/com/fullstack/demo/controller/TodoController.java
  - DAO: backend/src/main/java/com/fullstack/demo/dao/TodoDAOImpl.java
  - RestTemplate config: backend/src/main/java/com/fullstack/demo/config/RestTemplateConfig.java
  - Model: backend/src/main/java/com/fullstack/demo/model/Todo.java
  - Tests: backend/src/test/java/com/fullstack/demo/

- Frontend (client/demo/)
  - client/demo/package.json — scripts & packageManager
  - client/demo/angular.json — Angular project config
  - Entrypoint: client/demo/src/main.ts
  - App components: client/demo/src/app/
  - Test config: client/demo/tsconfig.spec.json

---

## Setup (prereqs & env)
- Backend:
  - JDK 21 (pom property java.version=21)
  - Maven (use provided wrapper `./mvnw` to avoid local mvn)
- Frontend:
  - Node + npm (package.json lists packageManager: "npm@11.16.0" — Node 18/20 recommended)
  - Run `npm ci` or `npm install` inside client/demo
- Network: Internet access required for backend external API calls
- Environment variables: none required for running the sample as-is

Assumptions: JDK 21 and a modern Node version are appropriate; packaging as WAR suggests possible external servlet container usage.

---

## Build & Test commands
Backend (from repo root):
- Build: cd backend && ./mvnw clean package
- Run (dev): cd backend && ./mvnw spring-boot:run
- Test: cd backend && ./mvnw test

Frontend (client/demo):
- Install: cd client/demo && npm ci
- Run dev server: cd client/demo && npm start  (ng serve)
- Build: cd client/demo && npm run build
- Test: cd client/demo && npm test

---

## Deployment & CI notes
- No CI/CD pipelines or Dockerfiles detected in this repository. Add GitHub Actions (or equivalent) to build and test both components.
- Backend packaging is `war` (see backend/pom.xml). For containerized deployments prefer building an executable JAR or using an embedded server configuration, or create a Dockerfile that runs the WAR in a servlet container.

---

## Dependencies & Versions (highlights)
- Spring Boot parent: 4.0.5 (backend/pom.xml)
- Java target: 21 (backend/pom.xml)
- Angular core: ^18.0.0 (client/demo/package.json)
- TypeScript: ~5.5.0 (client/demo/package.json)

---

## Observed Integration Points
- Backend endpoints:
  - GET /api/todos -> backend/src/main/java/com/fullstack/demo/controller/TodoController.java
  - GET /api/todos/{id}
- Backend calls external API https://jsonplaceholder.typicode.com/todos via RestTemplate in TodoDAOImpl
- Frontend currently appears minimal/static and does not contain a fully wired HttpClient call to the backend (consider adding an Angular service)

---

## Gaps & Recommendations
1. Add CI (GitHub Actions) that runs: backend ./mvnw test & package; client/demo npm ci & npm test & npm run build.
2. Add Dockerfile(s) for backend and frontend to standardize builds and deployments.
3. Decide whether backend should be an executable JAR instead of WAR; update packaging if standalone deployment is desired.
4. Add environment config management (application.properties / .env) and document expected ports and endpoints.
5. Add a small frontend HttpClient service to demonstrate end‑to‑end integration with the backend.

---

## Quick Start
1) Start backend (dev):
   cd backend && ./mvnw spring-boot:run
2) Start frontend (dev) in a separate terminal:
   cd client/demo && npm ci && npm start

Or build artifacts first:
   cd backend && ./mvnw clean package
   cd client/demo && npm run build

---

## Estimated time-to-understand & Confidence
- Estimated time for a new developer to be productive: ~4 hours (small codebase, local run & basic changes)
- Confidence score: ~88% (based on manifest files and source inspection; deployment intent and exact Node version are assumptions)

---

## Assumptions
- JDK 21 is intended due to pom.xml java.version property.
- Frontend packageManager entry suggests npm; local Node and npm versions may differ.
- WAR packaging indicates the project might target an external servlet container or may be reconfigured for an executable artifact.

---

If desired, next steps that can be prepared:
- Add GitHub Actions workflow to run build/test for both components.
- Add Dockerfile templates for backend and frontend.
- Add a small non-invasive frontend HttpClient service example to call /api/todos.

