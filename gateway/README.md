# Módulo: gateway (Spring Cloud Gateway)

API Gateway reactivo que expone las API de Products y Orders.

## Objetivo del módulo

- Registrar este servicio en Eureka.
- Configurar rutas para exponer endpoints de `products-service` y
  `orders-service`.

## Pasos a realizar por el estudiante

1. Abre `src/main/resources/application.yml` y localiza la sección
   `spring.cloud.gateway.routes`.
2. Agrega dos rutas usando `lb://` (carga balanceada vía Eureka):
    - Ruta `products`:
        - `id: products`
        - `uri: lb://products-service`
        - `predicates: - Path=/api/products/**`
    - Ruta `orders`:
        - `id: orders`
        - `uri: lb://orders-service`
        - `predicates: - Path=/api/orders/**`
3. Verifica que `eureka.client.service-url.defaultZone` apunte a
   `http://localhost:8761/eureka` (o la URL de tu Eureka).
4. Ejecuta el módulo:
   ```bash
   mvn -q -pl gateway spring-boot:run
   ```
5. Prueba las rutas a través del Gateway:
    - http://localhost:8080/api/products
    - http://localhost:8080/api/orders

Notas:

- La propiedad `spring.cloud.gateway.discovery.locator.enabled=true` permite
  enrutar automáticamente con el patrón `http://localhost:8080/SERVICE-ID/**`,
  pero en este lab queremos practicar rutas explícitas.
