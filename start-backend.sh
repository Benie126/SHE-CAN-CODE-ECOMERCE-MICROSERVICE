#!/usr/bin/env bash
set -e; mkdir -p logs
start(){ (cd "$1" && nohup mvn spring-boot:run > "../logs/$2.log" 2>&1 & echo $! > "../logs/$2.pid"); sleep 5; }
start service-registry registry; start product-service product; start user-service user; start notification-service notification; start order-service order; start api-gateway gateway
echo "Backend started. Eureka: http://localhost:8761 | Gateway: http://localhost:8080"
