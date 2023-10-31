package com.stackroute.supplierservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.stackroute.supplierservice.exceptions.FlightAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.FlightNotFoundException;
import com.stackroute.supplierservice.model.Flight;
import com.stackroute.supplierservice.repo.FlightSearchRepository;
import com.stackroute.supplierservice.serviceimpl.FlightServiceImpl;

@RestController
@ComponentScan(basePackages = { "com.stackroute.supplierservice" })
@RequestMapping("api/v1")
public class FlightSearchController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public FlightSearchRepository frepo;
	
//	@Autowired
//	public FlightDataRepository fdrepo;

	@Autowired
	public FlightServiceImpl flightServiceImpl;
	
	@GetMapping("/flight/{departureAirportName}/{arrivalAirportName}/{departureTime}")
	public ResponseEntity<Object> getFlightByData(@PathVariable("departureAirportName") String departureAirportName,
			@PathVariable("arrivalAirportName") String arrivalAirportName,
						@PathVariable("departureTime") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate departureTime) {

		try {
			List<Flight> flightlist = new ArrayList<>();
			for(Flight flight : frepo.findByDepartureAirportNameAndArrivalAirportName(departureAirportName, arrivalAirportName)){
				System.out.println(flight.toString());
				if(flight.getDepartureTime().isEqual(departureTime) ){
					
					flightlist.add(flight);
				}
			}
			return new ResponseEntity<>(flightlist, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	@GetMapping("/flight/{departureAirportName}/{arrivalAirportName}")
//	public ResponseEntity<Object> getFlightByData(@PathVariable("departureAirportName") String departureAirportName,
//												  @PathVariable("arrivalAirportName") String arrivalAirportName) {
//
//		try {
//			return new ResponseEntity<>(frepo.findByData(departureAirportName, arrivalAirportName), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

	@PostMapping("/flight")
	public ResponseEntity<String> addFlight(@RequestBody Flight flight) {

		try {
			String result = flightServiceImpl.addFlight(flight);
			logger.info("In controller - {}", "Flight added: " + flight);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (FlightAlreadyExistsException e) {
			logger.info("In controller - {}", "FlightId : " + flight.getFlightId() + " already exists.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("flight/{flightId}")
	public ResponseEntity<Object> getFlightById(@PathVariable("flightId") int flightId) {
		try {
			Flight flightById = flightServiceImpl.getFlightById(flightId);
			if (flightById != null) {
				logger.info("In controller - {}", "Found Flight with Id : " + flightId + " is: " + flightById);
				return new ResponseEntity<>(flightById, HttpStatus.OK);
			}
		} catch (FlightNotFoundException e) {
			logger.info("In controller - {}", "Flight with Id : " + flightId + " not found.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		logger.info("In controller - {}", "Id: " + flightId + " not found in database");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/flights")
	public List<Flight> getAllFlights() {
		return flightServiceImpl.findAllFlightAsList();
	}

	@PutMapping("/flight/update")
	public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight) {

		try {
			flightServiceImpl.updateFlight(flight);
			logger.info("In controller - {}", "flightId : " + flight.getFlightId() + " Flight is updated");
			return new ResponseEntity<>(flight, HttpStatus.OK);
		} catch (FlightNotFoundException e) {
			logger.info("In controller -{} flightId :" + flight.getFlightId() + " flight doesn't exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller -{} ", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/flight/delete/{id}")
	public ResponseEntity<String> deleteFlightById(@PathVariable("id") int flightId) {
		try {
			String result = flightServiceImpl.deleteFlightById(flightId);
			logger.info("In controller - {}", "FlightId :" + flightId + " Flight is deleted successfully");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (FlightNotFoundException e) {
			logger.info("In controller -{} flightId :" + flightId + " flight doesn't exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller -{} ", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/flights/{emailId}")
	public ResponseEntity<?> findFlightsByEmailId(@PathVariable String emailId){
		return new ResponseEntity<>(flightServiceImpl.findAllFlightsByEmailId(emailId),HttpStatus.OK);
	}


	
//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}
}
	
	
	
//	@GetMapping("/allFlights")
//	public List<Flight> getAllFlights(){
//		return frepo.findAll();		
//	}
//	
	//Getting list of Flight from location to destination
	


//	
//	// For ServiceProvider to add new Flight
//	
//		@PostMapping("/addFlights")
//		public String addFlight(@RequestBody Flight flight) {
//			try {
//			frepo.save(flight);
//			logger.info("In controller - {}", "Flight has been added");
//			return "Added Flight:" +flight.getFlightId();
//			} catch(Exception e) {
//				logger.info("In controller - {}", "failed to add Flight");
//				return "Flight Already Exists";
//			}
//		}
//		
//		@PostMapping("/addFlightData")
//		public String addFlightData(@RequestBody FlightData flight) {
//			try {
//			fdrepo.save(flight);
//			logger.info("In controller - {}", "Flight Data has been added");
//			return "Added Flight Data: "+flight.getFlight_id();
//			} catch(Exception e) {
//				logger.info("In controller - {}", "failed to add Flight");
//				return "Flight Data Already Exists";
//			}
//		}
//		
//		
//		@PutMapping("/updateFlightsDataById/{id}")
//		public ResponseEntity<Object> updateFlight(@RequestBody FlightData flight,@PathVariable("id") int flights_id) {
////			flight.setFlight_id(flights_id);
////			fdrepo.save(flight);
////			return flight;
//			
//			try {
//				flight.setFlight_id(flights_id);
////				fdrepo.save(flight)
////				return flight;
//				return new ResponseEntity<>(fdrepo.save(flight), HttpStatus.OK);
//			} catch (Exception e) {
//				return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		}	
		
		

