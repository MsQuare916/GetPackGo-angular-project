package com.stackroute.supplierservice.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.supplierservice.model.Hotel;


@Repository
public interface HotelSearchRepository extends MongoRepository<Hotel, Integer>{

	@Query("{'hotelName': :#{#hotelName}}")
	List<Hotel> findByData(@Param( "hotelName") String hotelName);
	
	@Query("{'hotel.hotelId':?0}")
	List<Hotel> findByHotel_Id(@Param("hotelId") int hotelId);
	List<Hotel> findAllByEmailId(String emailId);
}
