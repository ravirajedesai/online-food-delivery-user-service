package com.onlinefooddelivery.user_service.controller;

import com.onlinefooddelivery.user_service.dto.UserResponse;
import com.onlinefooddelivery.user_service.entity.Users;
import com.onlinefooddelivery.user_service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices services;

    @GetMapping
    ResponseEntity<Page<Users>>
    getAllUsers(@RequestParam(defaultValue = "1") int pageNo,
                @RequestParam(defaultValue = "3") int pageSize,
                @RequestParam(defaultValue = "userName") String sortBy){
        return ResponseEntity.status(HttpStatus.OK)
                .body(services.getAllUsers(pageNo-1,pageSize,sortBy));
    }
    @GetMapping("/{id}")
    ResponseEntity<Users> getUserById(@PathVariable Long id){
        Users byId=services.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byId);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id){
        services.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    ResponseEntity<Users> addUsers(@RequestBody Users users){
        Users add=services.addUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(add);
    }
    @GetMapping("/name/{userName}")
    ResponseEntity<UserResponse> getUserByName(@PathVariable String userName){
        UserResponse users=services.getUserByUserName(userName);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
