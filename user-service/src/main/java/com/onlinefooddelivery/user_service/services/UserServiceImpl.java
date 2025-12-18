package com.onlinefooddelivery.user_service.services;

import com.onlinefooddelivery.user_service.dto.UserResponse;
import com.onlinefooddelivery.user_service.entity.Users;
import com.onlinefooddelivery.user_service.exceptions.UserNotFound;
import com.onlinefooddelivery.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices{

    @Autowired
    UserRepository repository;

    @Override
    public Page<Users> getAllUsers(int pageNo,
                                   int pageSize,
                                   String sortBy) {
        Pageable pageable= PageRequest.of(
                pageNo,
                pageSize,
                Sort.by(sortBy).ascending());
        return repository.findAll(pageable);
    }
    @Override
    public Users getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new UserNotFound("User Not Found wit this Id: "+id));
    }
    @Override
    public void deleteUserById(Long id) {
    repository.deleteById(id);
    }
    @Override
    public Users addUser(Users users) {
        Users add=repository.save(users);
        return add;
    }

    @Override
    public UserResponse getUserByUserName(String userName) {
        Users user=repository.
                findUserByUserName(userName)
                .orElseThrow(()->new UserNotFound("User Not Found.."+userName));

        UserResponse response=new UserResponse();
        response.setUserName(user.getUserName());
        response.setUserMobile(user.getUserMobile());
        response.setUserEmail(user.getUserEmail());
        response.setUserAddress(user.getUserAddress());
        return response;
    }
}
