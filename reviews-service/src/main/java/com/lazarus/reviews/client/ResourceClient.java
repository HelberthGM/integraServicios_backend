package com.lazarus.reviews.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@FeignClient(
        name = "resource-service",
        url = "http://localhost:8081/api/resources" // tu microservicio de recursos
)
public interface ResourceClient {

    @GetMapping("/{id}")
    ResponseEntity<Object> getResourceById(@PathVariable("id") String id);
}
