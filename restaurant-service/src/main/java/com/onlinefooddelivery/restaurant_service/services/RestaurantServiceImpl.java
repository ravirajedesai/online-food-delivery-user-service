package com.onlinefooddelivery.restaurant_service.services;


import com.onlinefooddelivery.restaurant_service.entity.Restaurant;
import com.onlinefooddelivery.restaurant_service.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository repository;

    @Override
    public Page<Restaurant> getAllRestaurant(int pageNo,
                                             int pageSize,
                                             String sortBy) {
        Pageable pageable= PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return repository.findAll(pageable);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Restaurant Not Found with Id: "+id));
    }

    @Override
    public void deleteRestaurantById(Long id) {
    repository.deleteById(id);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantByNameAndFood(String restaurantName, String foodName) {
        return repository.findByRestaurantNameAndFoodName(restaurantName,foodName);
    }
}
