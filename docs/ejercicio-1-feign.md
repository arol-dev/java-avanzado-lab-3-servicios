# Ejercicio 1: Comunicación entre Servicios con Feign Client

## Objetivo

Aprender a comunicar microservicios de forma declarativa utilizando **Spring Cloud OpenFeign**. En este ejercicio, el servicio de `orders-service` necesita obtener información detallada de los productos desde `products-service`.

## Archivo a Completar

* `orders-service/src/main/java/com/example/labs/orders/clients/ProductsClient.java`
* `orders-service/src/main/java/com/example/labs/orders/OrdersServiceApplication.java` (Configuración)

## Guía Paso a Paso

### Paso 1: Habilitar Feign Clients

Para que Spring Boot escanee y cree los clientes Feign, debemos agregar la notación `@EnableFeignClients` en la clase principal.

**Archivo**: `orders-service/src/main/java/com/example/labs/orders/OrdersServiceApplication.java`

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // <--- Descomentar o agregar esta línea
public class OrdersServiceApplication {
    // ...
}
```

### Paso 2: Definir el Cliente Feign

Debemos indicarle a Feign a qué servicio llamar y qué endpoints consumir.

**Archivo**: `orders-service/src/main/java/com/example/labs/orders/clients/ProductsClient.java`

```java
// Definir el nombre del servicio (service-id) y la URL (opcional si usamos Eureka, pero útil para pruebas locales)
@FeignClient(name = "products-service", url = "http://localhost:8081")
public interface ProductsClient {

    // Mapear el endpoint GET /api/products/{id}
    @GetMapping("/api/products/{id}")
    Map<String, Object> getProduct(@PathVariable("id") int id);
}
```

### Paso 3: Imports Necesarios

Asegúrate de tener los siguientes imports para evitar errores de compilación:

```java
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
```

## Verificación

Para validar que tu solución funciona, ejecuta el test de integración que simula la comunicación:

```bash
mvn -Dtest=OrdersIntegrationTest test
```

## Conceptos Clave

| Concepto | Descripción |
| :--- | :--- |
| **@EnableFeignClients** | Habilita el escaneo de interfaces anotadas con `@FeignClient`. |
| **@FeignClient** | Declara una interfaz como un cliente REST. Spring genera la implementación en tiempo de ejecución. |
| **Service Discovery** | Si se omite la `url`, Feign usa el `name` para buscar la IP del servicio en Eureka. |
