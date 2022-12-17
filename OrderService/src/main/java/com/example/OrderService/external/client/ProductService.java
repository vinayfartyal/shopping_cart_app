package com.example.OrderService.external.client;

import com.example.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
//Whenever we are doing the request to ProductService, we need to pass token
//So, for that reason we need to add interceptors and those interceptors will
//handle all those request like whenever i am doing the request it will automatically
//add the authentication token in that particular request.
public interface ProductService {

    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    );

    default ResponseEntity<Void> fallback(Exception e) {
        throw new CustomException("Product Service is not available",
                "UNAVAILABLE",
                500);
    }

}
