package com.practcode.rating.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practcode.rating.entities.Rating;
import com.practcode.rating.repository.RatingRepository;
import com.practcode.rating.services.RatingService;

@Service
public class RatingServiceImpl  implements RatingService{

	@Autowired
	private RatingRepository repository;
	
	@Override
	public Rating create(Rating rating) {
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return repository.findAll();
	}

	@Override
	public List<Rating> getRatingByUser(String UserId) {
		return repository.findByUserId(UserId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return repository.findByHotelId(hotelId);
	}

}
