package com.stackroute.supplierservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection ="flightDB")
@Data
public class Flight {
	
	@Id
	private int flightId;
	private String flightName;
	private String departureAirportName;
	private LocalDate departureTime;
	private LocalTime startTime;
	private LocalTime endTime;
	private String arrivalAirportName;
	private LocalDate arrivalTime;
//	private LocalDate flightTime;
	private double flightCost;
	private String emailId;
	
}
















//@Id
//private int id;
//
//private String arrival_location;
//private String departure_location;
//private String departure_time;
//private String arrival_time;
//private double cost;	