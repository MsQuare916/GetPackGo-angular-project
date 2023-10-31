package com.stackroute.supplierservice.model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "trainDB")
@Data
public class Train {
	
	@Id
	private int trainId;
	
	private String trainName;
	private String departurePlatformName;
	private LocalDate departureTime;
	//private Map<String, LocalDate> trainJourneyDetails;
	private String arrivalPlatformName;
	private LocalDate arrivalTime;
	private double trainCost;
	private String emailId;
}







//@Id
//private int id;
//
//private String arrival_location;
//private String departure_location;
//
//@DBRef
//List<TrainData> train;