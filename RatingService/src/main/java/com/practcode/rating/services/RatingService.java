package com.practcode.rating.services;

import java.util.List;

import com.practcode.rating.entities.Rating;

public interface RatingService {

	//create
	Rating create(Rating rating);
	
	//get all ratings
	List<Rating> getRatings();
	
	//get all by UserId
	List<Rating> getRatingByUser(String UserId);
	
	//get All by hotel
	List<Rating> getRatingByHotelId(String hotelId);
}
