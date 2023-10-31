package com.stackroute.supplierservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.supplierservice.exceptions.FlightAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.FlightNotFoundException;
import com.stackroute.supplierservice.model.Flight;
import com.stackroute.supplierservice.repo.FlightSearchRepository;
import com.stackroute.supplierservice.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightSearchRepository flightRepo;
	
	@Override
	public String addFlight(Flight flight) throws FlightAlreadyExistsException {
		if(!flightRepo.existsById(flight.getFlightId())) {
			flightRepo.save(flight);
			return "Flight with Id : "+flight.getFlightId()+" successfully added";
		}
		throw new FlightAlreadyExistsException();
	}
	
	@Override
	public String updateFlight(Flight flight) throws FlightNotFoundException {
		if(flightRepo.existsById(flight.getFlightId())) {
			flightRepo.save(flight);
			return "Successfully updated the changes!!";
		}
		throw new FlightNotFoundException();
	}

	@Override
	public List<Flight> findAllFlightsByEmailId(String emailId) {
		return flightRepo.findAllByEmailId(emailId);
	}


	@Override
	public Flight getFlightById(int flightId) throws FlightNotFoundException {
		
		if(flightRepo.existsById(flightId)) {
			return flightRepo.findById(flightId).get();
		}
		throw new FlightNotFoundException();
	}

	@Override
	public String deleteFlightById(int flightId) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		if(flightRepo.existsById(flightId)) {
			flightRepo.deleteById(flightId);
			return "Flight with Id : "+flightId+" deleted";
		}
		throw new FlightNotFoundException();
	}
	
	@Override
	public List<Flight> findAllFlightAsList() {
		
		return flightRepo.findAll();
	}

	

	
	
}
