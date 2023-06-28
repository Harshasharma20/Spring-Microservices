package com.learn.user.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.user.service.UserService.entities.Rating;
import com.learn.user.service.UserService.external.service.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
	
	@Test
	void createRating() {
		Rating rating = new Rating();
		rating.setRatingId("10");
		rating.setUserId("11");
		rating.setHotelId("12");
		rating.setFeedback("This is created using feign client");
		ratingService.createRating(rating);

		System.out.println("new rating created");
			
	}
}
