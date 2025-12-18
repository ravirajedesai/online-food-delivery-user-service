package com.onlinefooddelivery.order_service.service;

import com.onlinefooddelivery.order_service.dto.RestaurantResponse;
import com.onlinefooddelivery.order_service.dto.RestaurantRequest;
import com.onlinefooddelivery.order_service.dto.UserResponse;
import com.onlinefooddelivery.order_service.entity.Order;
import com.onlinefooddelivery.order_service.exceptions.OrderNotFound;
import com.onlinefooddelivery.order_service.feignClient.RestaurantClient;
import com.onlinefooddelivery.order_service.feignClient.UserClient;
import com.onlinefooddelivery.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository repository;
    @Autowired
    RestaurantClient restaurantClient;
    @Autowired
    UserClient userClient;

    @Override
    public Page<Order> getAllOrder(
            int pageNo,
            int pageSize,
            String sortBy) {
        Pageable pageable= PageRequest.of(
                pageNo,
                pageSize,
                Sort.by(sortBy).ascending());
        return repository.findAll(pageable);
    }
    @Override
    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new OrderNotFound("Order Not Found By Id: "+id));
    }
    @Override
    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Order createOrder(RestaurantRequest request) {

        RestaurantResponse restaurantresponse=
                restaurantClient
                        .getRestaurantByNameAndFood(request.getRestaurantName(),
                        request.getFoodName());

        UserResponse userResponse=
                userClient.getUserByName(request.getUserName());

        Order order=new Order();
        order.setUserName(userResponse.getUserName());
        order.setUserMobile(userResponse.getUserMobile());
        order.setUserEmail(userResponse.getUserEmail());
        order.setUserAddress(userResponse.getUserAddress());
        order.setRestaurantName(restaurantresponse.getRestaurantName());
        order.setFoodName(restaurantresponse.getFoodName());
        order.setTotalAmount(restaurantresponse.getFoodPrice());

        return repository.save(order);
    }
}
