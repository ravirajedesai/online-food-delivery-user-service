package com.onlinefooddelivery.order_service.dto;

import lombok.Data;

@Data
public class RestaurantRequest {

    private String userName;
    private String restaurantName;
    private String foodName;
}
