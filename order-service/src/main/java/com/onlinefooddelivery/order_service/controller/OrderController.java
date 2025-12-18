package com.onlinefooddelivery.order_service.controller;

import com.onlinefooddelivery.order_service.dto.RestaurantRequest;
import com.onlinefooddelivery.order_service.dto.UserResponse;
import com.onlinefooddelivery.order_service.entity.Order;
import com.onlinefooddelivery.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping
    ResponseEntity<Page<Order>>
    getAllOrders(@RequestParam(defaultValue = "1") int pageNo,
                 @RequestParam(defaultValue = "2") int pageSize,
                 @RequestParam(defaultValue = "orderId") String sortBy){
        Page<Order> pageable=service.getAllOrder(pageNo-1,pageSize,sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(pageable);
    }
    @GetMapping("/{id}")
    ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order byId=service.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byId);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    ResponseEntity<Order> addOrder(@RequestBody RestaurantRequest request){
        Order add=service.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(add);
    }
}
