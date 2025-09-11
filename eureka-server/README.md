# Módulo: eureka-server

Servidor de descubrimiento de servicios (Netflix Eureka).

## Objetivo del módulo

- Ejecutar un servidor Eureka en el puerto 8761.
- Permitir que los demás servicios (gateway, products, orders) se registren y
  sean descubiertos.

## Pasos

1. Revisa `src/main/resources/application.yml` y confirma:
    - `server.port` es 8761.
    - `eureka.client.register-with-eureka=false` y `fetch-registry=false` (modo
      servidor).
2. Ejecuta el módulo:
   ```bash
   mvn -q -pl eureka-server spring-boot:run
   ```
3. Abre el dashboard: http://localhost:8761

No se requiere código adicional para este módulo.
