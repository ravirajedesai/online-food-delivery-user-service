package com.onlinefooddelivery.restaurant_service.services;

import com.onlinefooddelivery.restaurant_service.entity.Restaurant;
import com.onlinefooddelivery.restaurant_service.exception.RestaurantNotFound;
import com.onlinefooddelivery.restaurant_service.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RestaurantServiceImpl service;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant(
                1L,
                "Sanskriti",
                "Pune",
                null,
                1L,
                "Dal Khichdi",
                BigInteger.valueOf(120)
        );

        // manually inject uploadDir (since @Value doesn't work in unit test)
        service.uploadDir = "uploads/restaurants/";
    }

    @Test
    void uploadImage_success() throws Exception {
        // Arrange
        MockMultipartFile image = new MockMultipartFile(
                "image",
                "food.jpg",
                "image/jpeg",
                "dummy image content".getBytes()
        );

        when(repository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(repository.save(any(Restaurant.class))).thenReturn(restaurant);

        // Act
        String imageUrl = service.uploadImage(1L, image);

        // Assert
        assertNotNull(imageUrl);
        assertTrue(imageUrl.contains("uploads/restaurants/"));
        assertNotNull(restaurant.getImageUrl());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(restaurant);
    }

    @Test
    void uploadImage_restaurantNotFound() {
        // Arrange
        MockMultipartFile image = new MockMultipartFile(
                "image",
                "food.jpg",
                "image/jpeg",
                "dummy image content".getBytes()
        );

        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RestaurantNotFound.class, () ->
                service.uploadImage(99L, image)
        );

        verify(repository, times(1)).findById(99L);
        verify(repository, never()).save(any());
    }
}
