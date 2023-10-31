package com.stackroute.bookingservice.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.cdi.MongoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.stackroute.bookingservice.model.Booking;
import com.stackroute.bookingservice.model.Cabs;
import com.stackroute.bookingservice.model.Hotels;
import com.stackroute.bookingservice.model.Trains;


@Repository

public interface BookingRepository extends MongoRepository<Booking ,String>{
   // @Query
	//List<Booking> findByDate(Date date);


List<Booking> findAllByUserEmailId(String userEmailId);	

	

}