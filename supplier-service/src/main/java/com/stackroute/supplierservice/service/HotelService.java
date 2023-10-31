package com.stackroute.supplierservice.service;

import java.util.List;

import com.stackroute.supplierservice.exceptions.HotelAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.HotelNotFoundException;
import com.stackroute.supplierservice.model.Hotel;

public interface HotelService {
	public Hotel getHotelById(int hotelId) throws HotelNotFoundException;
	public String deleteHotelById(int hotelId) throws HotelNotFoundException;
	public List<Hotel> findAllHotelAsList();
	public String addHotel(Hotel hotel) throws HotelAlreadyExistsException, HotelNotFoundException;
	public String updateHotel(Hotel hotel) throws HotelNotFoundException;

	List<Hotel> findAllHotelsByEmailId(String emailId);
}
