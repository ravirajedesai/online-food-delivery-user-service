package com.onlinefooddelivery.restaurant_service.exception;

public class RestaurantNotFound extends RuntimeException{
    public RestaurantNotFound(String message) {
        super(message);
    }
}
