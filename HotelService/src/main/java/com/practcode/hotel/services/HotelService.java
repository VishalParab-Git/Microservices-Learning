package com.practcode.hotel.services;

import java.util.List;

import com.practcode.hotel.entities.Hotel;

public interface HotelService {

	//create
	
	Hotel create(Hotel hotel);
	
	//getAll
	List<Hotel> getAll();
	
	//get By id
	Hotel get(String id);
	
}
