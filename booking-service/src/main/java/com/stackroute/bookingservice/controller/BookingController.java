
package com.stackroute.bookingservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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

import com.stackroute.bookingservice.exception.RecordNotFoundexception;
import com.stackroute.bookingservice.model.Booking;
import com.stackroute.bookingservice.repository.BookingRepository;
import com.stackroute.bookingservice.service.BookingServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j

public class BookingController {

	@Autowired
	public BookingRepository bookRepo;

	public BookingServiceImpl bookingserviceImpl;

	// for user to book services
	@PostMapping("/Booking")
	public String book(@RequestBody Booking booking) {
		log.info("Making the booking with booking_id:" + booking.getBookingId());

		boolean present = bookRepo.findById(booking.bookingId).isPresent();
		if (!present) {
			booking.setBookingId(String.valueOf(UUID.randomUUID().toString().substring(0,5)));
			log.info("Made the booking with pnr:" + booking.getBookingId());
			bookRepo.save(booking);
			return "booking successful";
		}

		log.error("Booking Already Present");
		return "Booking Already made";

	}

	// for user to get details of booking
	@GetMapping("/bookingDetails/{bookingId}")
	public Optional<Booking> getBooking(@PathVariable String bookingId) {
		try {
			log.info("getting Ticket:" + bookingId);
			return bookRepo.findById(bookingId);
		} catch (Exception e) {
			log.error(e.toString());
			return bookRepo.findById(bookingId);
		}
	}

	// for user to Cancel Booking
	@DeleteMapping("/CancelBooking/{bookingId}")
	public Booking cancelBooking( @PathVariable String bookingId) {
		Booking dbResponse = bookRepo.findById(bookingId).get();
		dbResponse.setBooking_cancelled(true);
		log.info("saving Changes In Booked ticket Cancelled/checking In :" + bookingId);
		bookRepo.save(dbResponse);
		
		return dbResponse;
	}

//   
//	 //Admin to get all bookings on the date
//	   @GetMapping("/Booking/{date}/{bookingId}")
//	   public List<Booking> getBookingByDate(@PathVariable Date date,@PathVariable String bookingId){
//	   	log.info("getting Ticket: "+bookingId);
//	   	return  (List<Booking>) bookRepo.findByDate(date);

	@GetMapping("/AllBookings/{userEmailId}")
	public ResponseEntity<?> getBookingById(@PathVariable String userEmailId) {
		try {
			List<Booking> bookingById = bookRepo.findAllByUserEmailId(userEmailId);
			if (bookingById != null) {
				log.info("Found Booking with Id:" + userEmailId + "is:" + bookingById);
				return new ResponseEntity<>(bookingById, HttpStatus.OK);
			}

		} catch (RecordNotFoundexception e) {
			log.info("Booking with Id : " + userEmailId + " not found.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		log.info("Something  went wrong.");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	

//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}
//	
}
