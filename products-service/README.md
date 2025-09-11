# Módulo: products-service

Servicio REST que expone una lista de productos y consume el servicio de
`orders-service` vía Feign (comunicación interna).

## Objetivo del módulo

- Registrar el servicio en Eureka (`spring.application.name=products-service`).
- Exponer endpoint `GET /api/products` (ya provisto).
- Configurar un Feign Client para llamar a `orders-service`.

## Pasos a realizar por el estudiante

1. Revisa `src/main/resources/application.yml` y verifica:
    - `server.port: 8081`
    - `spring.application.name: products-service`
    - `eureka.client.service-url.defaultZone` apunta al Eureka:
      `http://localhost:8761/eureka`
2. Abre el Feign client `OrdersClient` en
   `src/main/java/.../clients/OrdersClient.java` y confirma:
    - La anotación `@FeignClient(name = "orders-service")` (descubrimiento vía
      Eureka).
    - El método mapea `GET /api/orders` del servicio de órdenes.
3. (Opcional) Crea un endpoint en este servicio que combine productos con
   órdenes usando el Feign client, por ejemplo `GET /api/products-with-orders`:
    - Inyecta `OrdersClient` y realiza la llamada.
    - Devuelve un objeto con ambas listas o alguna correlación simple.
4. Ejecuta el módulo:
   ```bash
   mvn -q -pl products-service spring-boot:run
   ```
5. Prueba:
    - Directo: http://localhost:8081/api/products
    - Vía Gateway (una vez configurado): http://localhost:8080/api/products

Notas:

- Asegúrate de que `orders-service` esté arrancado para probar la comunicación
  vía Feign.
