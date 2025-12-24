package com.onlinefooddelivery.restaurant_service.services;

import com.onlinefooddelivery.restaurant_service.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {
    Page<Restaurant> getAllRestaurant(int pageNo,int pageSize,String sortBy);
    Restaurant getRestaurantById(Long id);
    void deleteRestaurantById(Long id);
    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant getRestaurantByNameAndFood(
            String restaurantName,
            String foodName);

    String uploadImage(Long id, MultipartFile image);
}
