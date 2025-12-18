package com.onlinefooddelivery.order_service.service;

import com.onlinefooddelivery.order_service.dto.RestaurantRequest;
import com.onlinefooddelivery.order_service.entity.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    Page<Order> getAllOrder(int pageNo,int pageSize,String sortBy);
    Order getOrderById(Long id);
    void deleteOrderById(Long id);

    Order createOrder(RestaurantRequest request);
}
