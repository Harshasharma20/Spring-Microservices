package com.learn.hotel.HotelService.services;

import java.util.List;

import com.learn.hotel.HotelService.entities.Hotel;

public interface HotelService {
	
	//create
	public Hotel create(Hotel hotel);
	
	//get
	public Hotel getHotel(String hotelId);
	
	//getAll
	
	public List<Hotel> getAll();
	
	

}
