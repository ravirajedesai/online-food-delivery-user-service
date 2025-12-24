package com.onlinefooddelivery.restaurant_service.controller;

import com.onlinefooddelivery.restaurant_service.entity.Restaurant;
import com.onlinefooddelivery.restaurant_service.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService service;

    @GetMapping
    ResponseEntity<Page<Restaurant>>
    getAllRestaurants(@RequestParam int pageNo,
                      @RequestParam int pageSize,
                      @RequestParam String sortBy){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllRestaurant(pageNo,pageSize,sortBy));
    }
    @GetMapping("/{id}")
    ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getRestaurantById(id));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRestaurantById(@PathVariable Long id){
        service.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant){
        Restaurant add=service.addRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(add);
    }
    @GetMapping("/name/{restaurantName}/food/{foodName}")
    public ResponseEntity<Restaurant> getRestaurantByNameAndFood(
            @PathVariable("restaurantName") String restaurantName,
            @PathVariable("foodName") String foodName){
        Restaurant restaurant=service
                .getRestaurantByNameAndFood(restaurantName,foodName);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<String>
    uploadRestaurantImage(
            @PathVariable Long id,
            @PathVariable MultipartFile image
            ){
        String imageUrl=service.uploadImage(id,image);
        return ResponseEntity.ok(imageUrl);
    }
}
