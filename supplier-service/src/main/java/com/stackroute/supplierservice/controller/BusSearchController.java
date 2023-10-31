package com.stackroute.supplierservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.supplierservice.exceptions.BusAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.BusNotFoundException;
import com.stackroute.supplierservice.model.Bus;
import com.stackroute.supplierservice.repo.BusSearchRepository;
import com.stackroute.supplierservice.serviceimpl.BusServiceImpl;

@RestController
@ComponentScan(basePackages = {"com.stackroute.supplierservice"})
@RequestMapping("/api/v1")
public class BusSearchController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public BusSearchRepository brepo;
//	
//	@Autowired
//	public BusDataRepository bdrepo;
	
	@Autowired
	public BusServiceImpl busServiceImpl;
	
	@PostMapping("/bus")
	public ResponseEntity<String> addBus(@RequestBody Bus bus) {
		
	try {
		String result = busServiceImpl.addBus(bus);
		logger.info("In controller - {}", "Bus added: " + bus );
		return new ResponseEntity<>(result, HttpStatus.OK);
			} catch(BusAlreadyExistsException e) {
		logger.info("In controller - {}", "busId : " + bus.getBusId() + " already exists.");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
			} catch (Exception e) {
		logger.info("In controller - {}", "Something went wrong");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@GetMapping("bus/{busId}")
	public ResponseEntity<Object> getBusById(@PathVariable("busId") int busId) {
		try {
			Bus busById = busServiceImpl.getBusById(busId);
			if(busById != null) {
				logger.info("In controller - {}", "Found Bus with Id : " + busId + " is: " + busById);
				return new ResponseEntity<>(busById, HttpStatus.OK);
			}
		} catch (BusNotFoundException e){
			logger.info("In controller - {}", "Bus with Id : " + busId + " not fouund.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		logger.info("In controller - {}", "Id: " + busId + " not found in database");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/buses")
	public List<Bus> getAllBus(){
		return busServiceImpl.findAllBusAsList();
	}
	
	@PutMapping("/bus/update")
	public ResponseEntity<Bus> updateBus(@RequestBody Bus bus){
		
		try{
			busServiceImpl.updateBus(bus);
			logger.info("In controller - {}","busId : "+bus.getBusId()+" Bus is updated");
			return new ResponseEntity<>(bus,HttpStatus.OK);
		} catch (BusNotFoundException e) {
			logger.info("In controller -{} busId :"+bus.getBusId()+" bus doesn't exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller -{} ","Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/bus/delete/{id}")
	public ResponseEntity<String> deleteBusById(@PathVariable("id") int busId){
		try{
			String result = busServiceImpl.deleteBusById(busId);
			logger.info("In controller - {}", "BusId :"+busId+" Bus is deleted successfully");
			return new ResponseEntity<>(result, HttpStatus.OK);
			} catch(BusNotFoundException e) {
				logger.info("In controller -{} busId :"+busId+" bus doesn't exists");
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			} catch (Exception e) {
				logger.info("In controller -{} ","Something went wrong");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	
	@GetMapping("/bus/{departureStation}/{arrivalStation}/{departureTime}")
	public ResponseEntity<Object> getBusByData(@PathVariable("departureStation") String dep_loc,
											   @PathVariable("arrivalStation") String arr_loc,
											   @PathVariable("departureTime") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate departureTime ){
		try{
			List<Bus> busList=new ArrayList<>();
			for(Bus bus: brepo.findByDepartureStationAndArrivalStation(dep_loc,arr_loc)){
				if(bus.getDepartureTime().isEqual(departureTime)){
					busList.add(bus);
				}
			}
			return new ResponseEntity<>(busList,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Something went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buses/{emailId}")
	public ResponseEntity<?> findBusesByEmailId(@PathVariable String emailId){

		try {
			return new ResponseEntity<>(busServiceImpl.findAllBusesByEmailId(emailId), HttpStatus.OK);
		}
			catch(Exception e){
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}
}
	
//	@GetMapping("/allBuses")
//	public List<Bus> getAllBuses(){	
//		
//		return brepo.findAll();		
//	}
//	
//	//Getting list of Flight from location to destination
//	
//	
//	
//	// For ServiceProvider to add new Flight
//	
//		@PostMapping("/addBuses")
//		public String addBus(@RequestBody Bus bus) {
//			brepo.save(bus);
//		return "Added Bus:" +bus.getBus();
//		}
//		
//		@PostMapping("/addBusData")
//		public String addBusData(@RequestBody BusData bus) {
//			bdrepo.save(bus);
//			return "Added Bus: "+bus.getBus_id();
//		
//		}
//		
//		@PutMapping("/updateBusesData/{id}")
//		public BusData updateFlight(@RequestBody BusData bus,@PathVariable("id") int bus_id){
//			bus.setBus_id(bus_id);
//			bdrepo.save(bus);
//			return bus;
//			
//		}

