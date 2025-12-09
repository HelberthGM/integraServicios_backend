package com.lazarus.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client to communicate with reviews-service
 * Example usage: validate user permissions for reviews
 */
@FeignClient(
        name = "reviews-service",
        url = "http://localhost:8082"
)
public interface ReviewClient {

    @GetMapping("/api/ratings/{id}")
    ResponseEntity<Object> getRatingById(@PathVariable("id") String id);
}
