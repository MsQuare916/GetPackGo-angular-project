package com.stackroute.supplierservice.service;

import java.util.List;

import com.stackroute.supplierservice.exceptions.FlightAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.FlightNotFoundException;
import com.stackroute.supplierservice.model.Flight;

public interface FlightService {
	
	public Flight getFlightById(int flightId) throws FlightNotFoundException;
	public String deleteFlightById(int flightId) throws FlightNotFoundException;
	public List<Flight> findAllFlightAsList();
	public String addFlight(Flight flight) throws FlightAlreadyExistsException;
	public String updateFlight(Flight flight) throws FlightNotFoundException;
	List<Flight> findAllFlightsByEmailId(String emailId);
}