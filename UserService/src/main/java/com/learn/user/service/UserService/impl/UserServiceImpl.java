package com.learn.user.service.UserService.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.user.service.UserService.entities.Hotel;
import com.learn.user.service.UserService.entities.Rating;
import com.learn.user.service.UserService.entities.User;
import com.learn.user.service.UserService.exception.ResourceNotFoundException;
import com.learn.user.service.UserService.external.service.HotelService;
import com.learn.user.service.UserService.repositories.UserRepository;
import com.learn.user.service.UserService.services.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public User saveUser(User user) {
		//generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {	
		
		//get user from database with the help of user repository		
		User user1=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! : " +userId));
		
		//fetch rating of the above user from Rating service
		//http://localhost:8083/ratings/users/60fd1398-6206-47f6-8dff-3e0758c80b68
		Rating[] forObject = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/60fd1398-6206-47f6-8dff-3e0758c80b68"+user1.getUserId(), Rating[].class);
		logger.info("{}",forObject);
		
		List<Rating> ratings=Arrays.stream(forObject).toList();
		
		List<Rating> ratingList=ratings.stream().map(rating->{
			//api call to  hotel service to get the hotel
			//http://localhost:8082/hotels/8b71532d-5f3e-48bb-ac2c-25258ec8e8ce
			
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId()); 
			
			//logger.info("response status code:{} ",forEntity.getStatusCode());
			
			//set the hotel to rating
			rating.setHotel(hotel);
			
			//return the rating
			return rating;
			
		}).collect(Collectors.toList());
		
		user1.setRatings(ratingList);
		
		return user1;
	}
}
