package com.learn.hotel.HotelService.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.hotel.HotelService.entities.Hotel;
import com.learn.hotel.HotelService.exception.ResourceNotFoundException;
import com.learn.hotel.HotelService.repositories.HotelRepository;
import com.learn.hotel.HotelService.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("hotel is not found by given id!!"));
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

}
