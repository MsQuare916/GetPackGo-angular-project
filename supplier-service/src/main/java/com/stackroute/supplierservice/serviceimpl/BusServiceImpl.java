package com.stackroute.supplierservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.supplierservice.exceptions.BusAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.BusNotFoundException;
import com.stackroute.supplierservice.model.Bus;
import com.stackroute.supplierservice.repo.BusSearchRepository;
import com.stackroute.supplierservice.service.BusService;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	private BusSearchRepository busRepo;
	
	
	@Override
	public String addBus(Bus bus) throws BusAlreadyExistsException {
		if(!busRepo.existsById(bus.getBusId())) {
			busRepo.save(bus);
			return "Bus with Id"+bus.getBusId()+"added successfully";
		}
		throw new BusAlreadyExistsException();
	}
	
	@Override
	public String updateBus(Bus bus) throws BusNotFoundException {
		if(busRepo.existsById(bus.getBusId())) {
			busRepo.save(bus);
			return "Successfully updated the changes!!";
		}
		throw new BusNotFoundException();
	}

	@Override
	public List<Bus> findAllBusesByEmailId(String emailId) {
		return busRepo.findAllByEmailId(emailId);
	}

	@Override
	public Bus getBusById(int busId) throws BusNotFoundException {
		if(busRepo.existsById(busId)) {
			return busRepo.findById(busId).get();
		}
		throw new BusNotFoundException();
	}

	@Override
	public String deleteBusById(int busId) throws BusNotFoundException {
		if(busRepo.existsById(busId)) {
			busRepo.deleteById(busId);
			return "Bus with Id : "+busId+" deleted successfully";
		}
		throw new BusNotFoundException();
	}

	@Override
	public List<Bus> findAllBusAsList() {
		return busRepo.findAll();
	}

	

	

}
