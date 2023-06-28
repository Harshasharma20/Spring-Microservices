package com.learn.hotel.HotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.hotel.HotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String>{

}
