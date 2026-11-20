package com.practcode.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practcode.user.service.entities.User;


public interface UserRepository extends JpaRepository<User, String> {

}
