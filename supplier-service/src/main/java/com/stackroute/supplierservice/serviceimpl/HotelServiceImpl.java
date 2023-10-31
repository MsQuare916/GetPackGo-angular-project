package com.stackroute.supplierservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.supplierservice.exceptions.HotelAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.HotelNotFoundException;
import com.stackroute.supplierservice.model.Hotel;
import com.stackroute.supplierservice.repo.HotelSearchRepository;
import com.stackroute.supplierservice.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelSearchRepository hotelRepo;
	
	@Override
	public String addHotel(Hotel hotel) throws HotelAlreadyExistsException {
		if(!hotelRepo.existsById(hotel.getHotelId())) {
			hotelRepo.save(hotel);
			return "Hotel with Id : "+hotel.getHotelId()+" added successfully";
		}
		throw new HotelAlreadyExistsException();
	}
	
	@Override
	public String updateHotel(Hotel hotel) throws HotelNotFoundException {
		if(hotelRepo.existsById(hotel.getHotelId())) {
			hotelRepo.save(hotel);
			return "Successfully updated the changes!!";
		}
		throw new HotelNotFoundException();
	}

	@Override
	public List<Hotel> findAllHotelsByEmailId(String emailId) {
		return hotelRepo.findAllByEmailId(emailId);
	}

	@Override
	public Hotel getHotelById(int hotelId) throws HotelNotFoundException {
		
		if(hotelRepo.existsById(hotelId)) {
			return hotelRepo.findById(hotelId).get();
		}
		throw new HotelNotFoundException();
	}


	@Override
	public String deleteHotelById(int hotelId) throws HotelNotFoundException {
		if(hotelRepo.existsById(hotelId)) {
			hotelRepo.deleteById(hotelId);
			return "Hotel Id :" +hotelId+" is deleted successfully deleted";
		}
		throw new HotelNotFoundException();
	}

	@Override
	public List<Hotel> findAllHotelAsList() {
		
		return hotelRepo.findAll();
	}
	
}
