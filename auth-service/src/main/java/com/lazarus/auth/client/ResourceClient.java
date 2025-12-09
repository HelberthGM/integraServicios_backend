package com.lazarus.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client to communicate with resources-service
 * Example usage: validate if a resource exists before assigning permissions
 */
@FeignClient(
        name = "recursos-service",
        url = "http://localhost:8081"
)
public interface ResourceClient {

    @GetMapping("/api/resources/{id}")
    ResponseEntity<Object> getResourceById(@PathVariable("id") String id);

    @GetMapping("/api/resources")
    ResponseEntity<Object> getAllResources();
}
