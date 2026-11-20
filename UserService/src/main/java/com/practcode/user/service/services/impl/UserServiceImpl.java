package com.practcode.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practcode.user.service.entities.Hotel;
import com.practcode.user.service.entities.Rating;
import com.practcode.user.service.entities.User;
import com.practcode.user.service.exceptions.ResourceNotFoundException;
import com.practcode.user.service.external.services.HotelService;
import com.practcode.user.service.repositories.UserRepository;
import com.practcode.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		//generate Unique userId
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		//implements rating service calls
		return userRepository.findAll();
	}

	@Override
	public User getUSer(String userId) {
		User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !! :"+ userId));
		//fetch rating of the above user from RATING SERVICE
		//http://localhost:8083/ratings/users/e92209a2-8790-4b01-8716-68053c447a84
		//Static User
		//ArrayList<Rating> ratingsOfUser=restTemplate.getForObject("http://localhost:8083/ratings/users/e92209a2-8790-4b01-8716-68053c447a84", ArrayList.class);
		
		//Dynamic User
		Rating[] ratingsOfUser=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}", ratingsOfUser);
		
		List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
		
		List<Rating> ratingList=ratings.stream().map(rating->{
			//api call to hotel service to get the hotel
			//http://localhost:8082/hotels/43064a91-9643-4f4c-9a42-5709df6e4873
			
	//		ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
	//		logger.info("response status code: {}",forEntity.getStatusCode());
			
			//set the hotel to rating
			rating.setHotel(hotel);
			//return thr rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	}

	//get single user
	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(String userId, User User) {
		// TODO Auto-generated method stub
		
	}

}
