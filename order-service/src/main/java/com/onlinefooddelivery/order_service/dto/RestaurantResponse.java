package com.onlinefooddelivery.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantResponse {
    private Long restaurantId;
    private String restaurantName;
    private String restaurantLocation;
    private String foodName;
    private BigDecimal foodPrice;

}
