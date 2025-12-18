package com.onlinefooddelivery.restaurant_service.services;

import com.onlinefooddelivery.restaurant_service.entity.Restaurant;
import org.springframework.data.domain.Page;

public interface RestaurantService {
    Page<Restaurant> getAllRestaurant(int pageNo,int pageSize,String sortBy);
    Restaurant getRestaurantById(Long id);
    void deleteRestaurantById(Long id);
    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant getRestaurantByNameAndFood(
            String restaurantName,
            String foodName);
}
