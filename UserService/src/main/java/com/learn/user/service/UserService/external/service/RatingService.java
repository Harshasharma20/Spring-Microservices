package com.learn.user.service.UserService.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.learn.user.service.UserService.entities.Rating;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	//POST
	@PostMapping("/ratings")
	public Rating createRating(Rating values);  //jab  humare pass user defined data nhi hai toh hum parameter mei Map<String, Object> values likh saktey hai 
	//lekin humare pass user defined values hai rating class ki isliye Rating pass kiya hai argument mei
	
	//put
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId,Rating rating);
	
	//delete
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);

}
