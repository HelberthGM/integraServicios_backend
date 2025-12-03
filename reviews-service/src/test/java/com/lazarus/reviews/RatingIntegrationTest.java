package com.lazarus.reviews;

import com.lazarus.reviews.client.ResourceClient;
import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RatingIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @SuppressWarnings("removal")
    @MockBean
    private ResourceClient resourceClient;

    private String baseUrl;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/ratings";
        ratingRepository.deleteAll();
    }

    @SuppressWarnings("unchecked")
@Test
    void testCreateRating_ResourceExists() {
        // Mock: el recurso SÍ existe
        Mockito.when(resourceClient.getResourceById(any()))
                .thenReturn(ResponseEntity.ok(Map.of("id", "fake", "name", "Test")));

        CreateRatingRequest req = new CreateRatingRequest(
                UUID.randomUUID(), 4.5, 4.0, 5.0
        );

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response =
                restTemplate.postForEntity(baseUrl, req, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).containsKey("id");
    }

    @SuppressWarnings({ "null", "rawtypes" })
@Test
    void testCreateRating_ResourceDoesNotExist() {
        // Mock: el recurso NO existe → devolvemos 500 como el microservicio real
        Mockito.when(resourceClient.getResourceById(any()))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());


        CreateRatingRequest req = new CreateRatingRequest(
                UUID.randomUUID(), 3.0, 3.0, 3.0
        );

        ResponseEntity<Map> response =
                restTemplate.postForEntity(baseUrl, req, Map.class);

                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        assertTrue(response.getBody().containsValue("UNEXPECTED_ERROR"));


    }
}
