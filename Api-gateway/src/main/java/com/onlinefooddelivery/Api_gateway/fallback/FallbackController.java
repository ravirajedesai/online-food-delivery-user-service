package com.onlinefooddelivery.Api_gateway.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/fallback/user")
    public ResponseEntity<String> userServiceFallback(){
        return ResponseEntity.ok("User Service is Down..");
    }
    @GetMapping("/fallback/restaurant")
    public ResponseEntity<String> restaurantServiceFallback(){
        return ResponseEntity.ok("Restaurant Service is Down..");
    }
    @GetMapping("/fallback/order")
    public ResponseEntity<String> orderServiceFallback(){
        return ResponseEntity.ok("Order Service is Down..");
    }

}
