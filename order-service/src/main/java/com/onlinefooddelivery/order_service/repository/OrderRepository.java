package com.onlinefooddelivery.order_service.repository;

import com.onlinefooddelivery.order_service.dto.UserResponse;
import com.onlinefooddelivery.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
