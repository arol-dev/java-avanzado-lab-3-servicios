# Spring Cloud + Netflix Eureka: Tienda de Microservicios

🎓 **Laboratory: Java Avanzado - Spring Cloud**

Este repositorio contiene un laboratorio diseñado para aprender los fundamentos de **Spring Cloud**, **Service Discovery (Eureka)**, **Declarative Clients (Feign)** y **API Gateway (Spring Cloud Gateway)**.

## 🏢 Escenario

Simulamos una arquitectura de ecommerce simplificada donde:

- **Products Service**: Gestiona el catálogo de productos (puerto 8081).
- **Orders Service**: Gestiona órdenes de compra (puerto 8082).
- **Gateway**: Punto de entrada único para el sistema (puerto 8080).
- **Eureka Server**: Registro de servicios (puerto 8761).

El objetivo es conectar estos servicios que actualmente funcionan de forma aislada.

## 🎯 Objetivos de Aprendizaje

- Comprender la función de un **Discovery Service** (Eureka).
- Implementar comunicación entre microservicios usando **OpenFeign**.
- Configurar un **API Gateway** para enrutamiento dinámico.
- Escribir y pasar **Tests de Integración** que verifiquen la arquitectura.

## 🛠️ Stack Tecnológico

- **Java 17**
- **Spring Boot 3.3.3**
- **Spring Cloud 2023.0.3**
- **Netflix Eureka**
- **OpenFeign**
- **Spring Cloud Gateway**
- **WireMock** (para tests)

## 📂 Estructura del Proyecto

```
.
├── docker-compose.yml
├── docs/                   <-- 📘 DOCUMENTACIÓN DE EJERCICIOS
├── eureka-server/
├── gateway/                <-- Ejercicio 2
├── orders-service/         <-- Ejercicio 1
└── products-service/
```

## 📝 Ejercicios

| # | Ejercicio | Archivo de Test | Documentación |
|---|---|---|---|
| 1 | **Comunicación con Feign** | [OrdersIntegrationTest.java](orders-service/src/test/java/com/example/labs/orders/OrdersIntegrationTest.java) | [Ver Guía](docs/ejercicio-1-feign.md) |
| 2 | **Api Gateway Routing** | [GatewayIntegrationTest.java](gateway/src/test/java/com/example/labs/gateway/GatewayIntegrationTest.java) | [Ver Guía](docs/ejercicio-2-gateway.md) |

## 🚀 Flujo de Trabajo

1. **Leer la guía** del ejercicio en la carpeta `docs/`.
2. **Abrir el archivo de test** indicado. Notarás que el test falla o falta implementación.
3. **Completar los TODOs** en el código fuente (`src/main`) siguiendo la guía.
4. **Verificar y Ejecutar** el test:

    ```bash
    # Para Ejercicio 1
    mvn -pl orders-service -Dtest=OrdersIntegrationTest test
    
    # Para Ejercicio 2
    mvn -pl gateway -Dtest=GatewayIntegrationTest test
    ```

## 🏃 Cómo Ejecutar (Manual)

Si deseas levantar todo el entorno para pruebas manuales:

1. Asegúrate de tener **Docker** en ejecución.
2. Ejecuta:

    ```bash
    docker-compose up --build
    ```

3. Accede a:
    - **Eureka Dashboard**: <http://localhost:8761>
    - **Gateway**: <http://localhost:8080>

## ❌ Troubleshooting

| Error | Causa Posible | Solución |
|---|---|---|
| `Connection refused` | Servicios caídos o puertos ocupados. | Revisa `docker-compose ps` y libera puertos. |
| `Load balancer does not contain an instance` | El servicio no se ha registrado en Eureka. | Espera unos segundos o revisa logs de Eureka. |
| `FeigClient bean not found` | Falta `@EnableFeignClients`. | Revisa el Ejercicio 1. |
