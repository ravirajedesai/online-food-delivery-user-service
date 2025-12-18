package com.onlinefooddelivery.restaurant_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(nullable = false,unique = true)
    private String restaurantName;

    @Column(nullable = false)
    private String restaurantLocation;

    @Column(nullable = false)
    private Long foodId;
    @Column(nullable = false)
    private String foodName;
    @Column(nullable = false)
    private BigInteger foodPrice;

}
