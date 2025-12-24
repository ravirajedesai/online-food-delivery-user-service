package com.onlinefooddelivery.restaurant_service.services;


import com.onlinefooddelivery.restaurant_service.entity.Restaurant;
import com.onlinefooddelivery.restaurant_service.exception.RestaurantNotFound;
import com.onlinefooddelivery.restaurant_service.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository repository;

    @Value("${image.upload.dir}$")
    private String uploadDir;

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
                        new RestaurantNotFound("Restaurant Not Found with Id: "+id));
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

    @Override
    public String uploadImage(Long id, MultipartFile image) {
        Restaurant restaurant=repository
                .findById(id)
                .orElseThrow(()->
                        new RestaurantNotFound("Restaurant Not Found.."));
        try {
            String fileName=System.currentTimeMillis()+"_"+image.getOriginalFilename();
            Path filepath= Paths.get(uploadDir+fileName);
            Files.createDirectories(filepath.getParent());
            Files.write(filepath,image.getBytes());
            String imageUrl=uploadDir+fileName;

            restaurant.setImageUrl(imageUrl);
            repository.save(restaurant);
            return imageUrl;
        } catch (Exception e) {
            throw new RuntimeException("Image Upload Failed..",e);
        }
    }
}
