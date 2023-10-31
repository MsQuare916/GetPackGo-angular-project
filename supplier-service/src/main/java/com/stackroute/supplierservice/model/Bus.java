package com.stackroute.supplierservice.model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "busDB")
@Data
public class Bus {
	
	@Id
	private int busId;
	
	private String busName;
	private String departureStation;
	private LocalDate departureTime;
	//private Map<String, LocalDate> busJourneyDetails;
	private String arrivalStation;
	private LocalDate arrivalTime;
	private double cost;
	private String emailId;
	
}


//@Id
//private int id;
//
//private String arrival_location;
//private String departure_location;
//
//@DBRef
//List<BusData> bus;