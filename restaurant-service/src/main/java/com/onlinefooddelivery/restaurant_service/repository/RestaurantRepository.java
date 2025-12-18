package com.onlinefooddelivery.restaurant_service.repository;

import com.onlinefooddelivery.restaurant_service.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findByRestaurantNameAndFoodName(
            String restaurantName,
            String foodName);
}
