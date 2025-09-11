# Java Avanzado - Lab 3 - Servicios

Este laboratorio contiene 4 módulos:

- eureka-server
- gateway
- products-service
- orders-service

Objetivo: configurar un ecosistema de microservicios con Service Discovery (
Eureka), API Gateway (Spring Cloud Gateway) y comunicación interna mediante
Feign Clients.

## Cómo ejecutar (orden recomendado)

1. Compilar todo: `mvn -q -DskipTests package`
2. Levantar Eureka: `mvn -q -pl eureka-server spring-boot:run`
3. Levantar Gateway: `mvn -q -pl gateway spring-boot:run`
4. Levantar Products: `mvn -q -pl products-service spring-boot:run`
5. Levantar Orders: `mvn -q -pl orders-service spring-boot:run`

## Endpoints de prueba

- Eureka Dashboard: http://localhost:8761
- Products directo: http://localhost:8081/api/products
- Orders directo: http://localhost:8082/api/orders
- A través del Gateway (rutas a configurar por el estudiante):
    - http://localhost:8080/api/products
    - http://localhost:8080/api/orders

## Objetivo

- Crear un nuevo endpoint orders with products expuesto desde el gateway
- Usar Feign para comunicar con el servicio de orders con el de products y
  retornar una respuesta con ambos datos