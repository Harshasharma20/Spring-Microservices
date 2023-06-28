package com.learn.rating.RatingService.services;

import java.util.List;

import com.learn.rating.RatingService.entities.Rating;

public interface RatingService {
	
	//create rating
	Rating create(Rating rating);
	
	//Get all ratings
	List<Rating> getRatings();
	
	//Get all by UserId
	List<Rating> getRatingByUserId(String userId);
	
	//get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);
}
