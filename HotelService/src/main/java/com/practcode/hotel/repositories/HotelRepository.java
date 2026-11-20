package com.practcode.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practcode.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
