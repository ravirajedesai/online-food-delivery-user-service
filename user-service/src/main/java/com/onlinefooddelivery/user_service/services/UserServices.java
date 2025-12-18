package com.onlinefooddelivery.user_service.services;

import com.onlinefooddelivery.user_service.dto.UserResponse;
import com.onlinefooddelivery.user_service.entity.Users;
import org.springframework.data.domain.Page;

public interface UserServices {
    Page<Users> getAllUsers(int pageNo,int pageSize,String sortBy);
    Users getUserById(Long id);
    void deleteUserById(Long id);
    Users addUser (Users users);

    UserResponse getUserByUserName(String userName);
}
