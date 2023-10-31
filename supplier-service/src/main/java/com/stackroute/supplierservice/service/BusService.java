package com.stackroute.supplierservice.service;

import java.util.List;

import com.stackroute.supplierservice.exceptions.BusAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.BusNotFoundException;
import com.stackroute.supplierservice.model.Bus;

public interface BusService {
	
	public Bus getBusById(int busId) throws BusNotFoundException;
	public String deleteBusById(int busId) throws BusNotFoundException;
	public List<Bus> findAllBusAsList();
	public String addBus(Bus bus) throws BusAlreadyExistsException;
	public String updateBus(Bus bus) throws BusNotFoundException;
	List<Bus> findAllBusesByEmailId(String emailId);
}
