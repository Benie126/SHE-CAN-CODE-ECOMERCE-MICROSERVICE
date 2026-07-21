# Simple E-Commerce Microservices

A backend-focused academic project using Java 17, Spring Boot, Maven, Eureka, API Gateway, OpenFeign, separate H2 file databases, and a simple React interface.

## Architecture and ports

| Component | Port |
|---|---:|
| React frontend | 5173 |
| API Gateway | 8080 |
| Product Service | 8081 |
| User Service | 8082 |
| Order Service | 8083 |
| Notification Service | 8084 |
| Eureka Discovery | 8761 |

The browser calls only `http://localhost:8080`. Each business service stores data in its own local H2 file under its module's `data` directory. No MySQL or PostgreSQL installation is required.

## Requirements

- JDK 17
- Maven 3.6+
- Node.js 18+
- npm

## Run

Windows backend:

```powershell
.\start-backend.ps1
```

Linux/macOS backend:

```bash
./start-backend.sh
```

Frontend:

```bash
cd ecommerce-frontend
npm install
npm run dev
```

Open `http://localhost:5173` and Eureka at `http://localhost:8761`.

Default local administrator: `admin@shop.local` / `Admin123!`.

## Manual backend startup order

Run `mvn spring-boot:run` inside: service-registry, product-service, user-service, notification-service, order-service, then api-gateway.

## Reset data

Stop the services and delete each service's `data` directory. Seed data will be recreated on the next startup.
