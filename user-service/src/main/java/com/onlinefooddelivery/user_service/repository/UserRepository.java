package com.onlinefooddelivery.user_service.repository;

import com.onlinefooddelivery.user_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findUserByUserName(String userName);
}
