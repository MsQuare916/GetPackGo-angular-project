package com.stackroute.bookingservice.service;

import com.stackroute.bookingservice.exception.RecordNotFoundexception;
import com.stackroute.bookingservice.model.Booking;

public interface BookingService {

	
public Booking getBookingById(String userEmailId)throws RecordNotFoundexception;


}