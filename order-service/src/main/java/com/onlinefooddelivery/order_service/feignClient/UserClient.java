package com.onlinefooddelivery.order_service.feignClient;

import com.onlinefooddelivery.order_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/user/name/{userName}")
    UserResponse getUserByName(@PathVariable String userName);
}
