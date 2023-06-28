package com.learn.user.service.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.user.service.UserService.entities.User;

public interface UserRepository extends JpaRepository<User,String>{

}
