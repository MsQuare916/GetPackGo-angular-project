package com.stackroute.supplierservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.supplierservice.exceptions.HotelAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.HotelNotFoundException;
import com.stackroute.supplierservice.model.Hotel;
import com.stackroute.supplierservice.repo.HotelSearchRepository;
import com.stackroute.supplierservice.serviceimpl.HotelServiceImpl;

@RestController
@ComponentScan(basePackages = { "com.stackroute.supplierservice" })
@RequestMapping("api/v1")
public class HotelSearchController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public HotelSearchRepository hrepo;


	@Autowired
	public HotelServiceImpl hotelServiceImpl;

	// For ServiceProvider to add new Flight

	@PostMapping("/hotel")
	public ResponseEntity<String> addHotel(@RequestBody Hotel hotel) {
		try {
			String result = hotelServiceImpl.addHotel(hotel);
			logger.info("In controller - {}", "Hotel added: " + hotel);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (HotelAlreadyExistsException e) {
			logger.info("In controller - {}", "Hotel with Id :" + hotel.getHotelId() + "Already exists ");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<Object> getHotelById(@PathVariable("hotelId") int hotelId) {
		try {
			Hotel hotelById = hotelServiceImpl.getHotelById(hotelId);
			if (hotelById != null) {
				logger.info("In controller - {}", "Found Hotel with Id : " + hotelId + " is: " + hotelById);
				return new ResponseEntity<>(hotelById, HttpStatus.OK);
			}
		} catch (HotelNotFoundException e) {
			logger.info("In controller - {}", "Hotel with hotelId : " + hotelId + " not fouund.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		logger.info("In controller - {}", "Something  went wrong.");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/hotelname/{hotelName}")
	public ResponseEntity<Object> getHotelByData(@PathVariable("hotelName") String hotelName) {

		try {
			return new ResponseEntity<>(hrepo.findByData(hotelName), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}



	}

//	@GetMapping("/hotel/{hotelName}")
//	public List<Hotel> getHotelByName(@PathVariable("hotelName") String hotelName) {
//		try {
//			List<Hotel> hotelByName = hrepo.findByData(hotelName);
//			return (hotelByName);
//		}  catch (Exception e) {
//			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//
//	}

	@GetMapping("/hotels")
	public List<Hotel> getAllHotels() {
		return hotelServiceImpl.findAllHotelAsList();
	}

	// Getting list of Flight from location to destination

//		@PostMapping("/addHotelData")
//		public String addHotelData(@RequestBody HotelData hotel) {
//			hdrepo.save(hotel);
//			return "Added Hotel: "+hotel.getHotel_id();
//		
//		}

	@PutMapping("/hotel/update")
	public ResponseEntity<String> updateHotel(@RequestBody Hotel hotel) {
		try {
			String result = hotelServiceImpl.updateHotel(hotel);
			logger.info("In controller - {}", "hotelId : " + hotel.getHotelId() + " Flight is updated");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (HotelNotFoundException e) {
			logger.info("In controller -{} hotelId :" + hotel.getHotelId() + " hotel doesn't exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller -{} ", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/hotel/delete/{id}")
	public ResponseEntity<String> deleteHotelById(@PathVariable("id") int hotelId) {
		try {
			String result = hotelServiceImpl.deleteHotelById(hotelId);
			logger.info("In controller - {}", "hotelId :" + hotelId + " Hotel is deleted successfully");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (HotelNotFoundException e) {
			logger.info("In controller -{} hotelId :" + hotelId + " hotel doesn't exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller -{} ", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/hotels/{emailId}")
	public ResponseEntity<?> findHotelsByEmailId(@PathVariable String emailId){
		return new ResponseEntity<>(hotelServiceImpl.findAllHotelsByEmailId(emailId),HttpStatus.OK);
	}
	
//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}
}
