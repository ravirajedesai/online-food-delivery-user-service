package com.onlinefooddelivery.user_service.dto;

import lombok.Data;

@Data
public class UserResponse {

    private String userName;
    private String userEmail;
    private String userMobile;
    private String userAddress;
}
