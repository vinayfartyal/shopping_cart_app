package com.example.OrderService.external.client;


import com.example.OrderService.exception.CustomException;
import com.example.OrderService.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")

//Whenever we are doing the request to PaymentService, we need to pass token
//So, for that reason we need to add interceptors and those interceptors will
//handle all those request like whenever i am doing the request it will automatically
//add the authentication token in that particular request.
public interface PaymentService {

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    default ResponseEntity<Long> fallback(Exception e) {
        throw new CustomException("Payment Service is not available",
                "UNAVAILABLE",
                500);
    }
}