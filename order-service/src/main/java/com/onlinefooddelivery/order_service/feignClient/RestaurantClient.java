package com.onlinefooddelivery.order_service.feignClient;

import com.onlinefooddelivery.order_service.dto.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Restaurant-service")
public interface RestaurantClient {

    @GetMapping("/restaurant/name/{restaurantName}/food/{foodName}")
    RestaurantResponse getRestaurantByNameAndFood(
            @PathVariable("restaurantName") String restaurantName,
            @PathVariable("foodName") String foodName);
}
