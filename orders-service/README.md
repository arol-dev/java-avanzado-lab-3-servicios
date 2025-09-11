# Módulo: orders-service

Servicio REST que expone una lista de órdenes y puede consumir
`products-service` vía Feign (comunicación interna).

## Objetivo del módulo

- Registrar el servicio en Eureka (`spring.application.name=orders-service`).
- Exponer endpoint `GET /api/orders` (ya provisto).
- Configurar un Feign Client para llamar a `products-service` (ya definido como
  `ProductsClient`).

## Pasos a realizar por el estudiante

1. Revisa `src/main/resources/application.yml` y verifica:
    - `server.port: 8082`
    - `spring.application.name: orders-service`
    - `eureka.client.service-url.defaultZone` apunta al Eureka:
      `http://localhost:8761/eureka`
2. Abre el Feign client `ProductsClient` en
   `src/main/java/.../clients/ProductsClient.java` y confirma:
    - La anotación `@FeignClient(name = "products-service")` (descubrimiento vía
      Eureka).
    - El método mapea `GET /api/products`.
3. (Opcional) Crea un endpoint en Orders que use `ProductsClient` para
   enriquecer órdenes con información de productos.
4. Ejecuta el módulo:
   ```bash
   mvn -q -pl orders-service spring-boot:run
   ```
5. Prueba:
    - Directo: http://localhost:8082/api/orders
    - Vía Gateway (una vez configurado): http://localhost:8080/api/orders

Notas:

- Asegúrate de que `products-service` esté arrancado para probar la comunicación
  vía Feign.
