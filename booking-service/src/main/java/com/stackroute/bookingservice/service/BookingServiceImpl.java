package com.stackroute.bookingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookingservice.exception.RecordNotFoundexception;
import com.stackroute.bookingservice.model.Booking;
import com.stackroute.bookingservice.repository.BookingRepository;
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	
	
	@Override
	public Booking getBookingById(String userEmailId) throws RecordNotFoundexception {
		
		if(bookingRepository.existsById(userEmailId)) {
			return bookingRepository.findById(userEmailId).get();
		}
		throw new RecordNotFoundexception();
}
	}

