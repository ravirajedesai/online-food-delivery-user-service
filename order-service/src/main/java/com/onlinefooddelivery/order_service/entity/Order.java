package com.onlinefooddelivery.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String userName;
    private String userMobile;
    private String userEmail;
    private String userAddress;

    private String restaurantName;
    private String foodName;

    private BigDecimal totalAmount;

}
