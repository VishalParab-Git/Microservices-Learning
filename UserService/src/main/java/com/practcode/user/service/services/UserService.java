package com.practcode.user.service.services;

import java.util.List;

import com.practcode.user.service.entities.User;

public interface UserService {

	//User Operations
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given userId
	User getUSer(String userId);
	
	//TODO: delete
	void deleteUser(String userId);
	
	//TODO: update
	void updateUser(String userId,User User);
	
	
}
