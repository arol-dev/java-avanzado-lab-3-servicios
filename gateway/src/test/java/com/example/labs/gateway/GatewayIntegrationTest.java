package com.example.labs.gateway;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class GatewayIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldRouteToProductsService() {
        // Mock del servicio de productos
        stubFor(get(urlEqualTo("/api/products"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\": 1, \"name\": \"Product Mock\"}]")));

        // La prueba falla si el Gateway no tiene la ruta configurada
        webTestClient.get().uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Product Mock");
    }
}
