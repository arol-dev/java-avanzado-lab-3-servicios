# Ejercicio 2: Enrutamiento con Spring Cloud Gateway

## Objetivo

Configurar un API Gateway para centralizar las peticiones a nuestros microservicios. Aprenderás a definir rutas usando `application.yml`.

## Archivo a Completar

* `gateway/src/main/resources/application.yml`

## Guía Paso a Paso

### Paso 1: Configurar Rutas

Necesitamos que el Gateway redirija:

1. Peticiones a `/api/products/**` hacia `products-service`.
2. Peticiones a `/api/orders/**` hacia `orders-service`.
3. Usaremos `lb://` (Load Balancer) para que resuelva la dirección usando Eureka.

**Archivo**: `gateway/src/main/resources/application.yml`

Busca la sección `spring.cloud.gateway.routes` y agrega lo siguiente:

```yaml
      routes:
        - id: products-service
          uri: lb://products-service
          predicates:
            - Path=/api/products/**
        - id: orders-service
          uri: lb://orders-service
          predicates:
            - Path=/api/orders/**
```

**Nota**: Asegúrate de respetar la indentación (2 espacios) de YAML.

## Verificación

Para validar que el Gateway enruta correctamente, ejecuta el test de integración:

```bash
mvn -Dtest=GatewayIntegrationTest test
```

## Conceptos Clave

| Concepto | Descripción |
| :--- | :--- |
| **Route ID** | Identificador único para la ruta. |
| **URI (lb://)** | Indica que el destino se resuelve mediante el Load Balancer (Eureka). |
| **Predicates** | Condiciones que debe cumplir la petición para coincidir con la ruta (ej. el Path). |
