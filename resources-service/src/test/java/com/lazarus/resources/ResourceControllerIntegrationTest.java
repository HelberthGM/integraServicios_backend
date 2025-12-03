package com.lazarus.resources;

import com.lazarus.resources.model.Resource;
import com.lazarus.resources.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResourceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/api/resources";
        resourceRepository.deleteAll();
    }

    @SuppressWarnings("null")
    @Test
    void testGetResourceById() {
        // Arrange
        Resource r = new Resource();
        r.setId(UUID.randomUUID());
        r.setName("Test Resource");
        resourceRepository.save(r);

        // Act
        ResponseEntity<Resource> response =
                restTemplate.getForEntity(baseUrl + "/" + r.getId(), Resource.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Test Resource");
    }

    @Test
    void testGetResourceNotFound() {
        UUID randomId = UUID.randomUUID();

        ResponseEntity<String> response =
                restTemplate.getForEntity(baseUrl + "/" + randomId, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    // @Test
    // @DisplayName("HU-06 - Debe rechazar préstamo si no existe reserva previa")
    // void shouldThrowException_WhenRegisterLoan_WithoutExistingReservation() {
    //     // Given: No existe reserva
    //     when(reservationRepository.findById(100L)).thenReturn(Optional.empty());
    
    //     // When & Then
    //     BusinessException exception = assertThrows(BusinessException.class, () -> {
    //         loanService.registerLoan(100L, employeeId, LocalDateTime.now());
    //     });
    
    //     assertEquals("No existe una reserva vigente para este préstamo", exception.getMessage());
    //     verify(loanRepository, never()).save(any(Loan.class));
    // }
}

