package com.stackroute.supplierservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document (collection = "hotelDB")
@Data
public class Hotel {
	
	@Id
	private int hotelId;
	
	private String hotelAddress;
	
	private String hotelName;
	private String hotelOwnerName;
	
	private double roomCostPerDay;
	private String summary;
	private String emailId;
	
}
















//	@Id
//	private int id;
//	
//	private Date arrivalDate;
//	private Date departureDate;
//	
//	@DBRef
//	List<HotelData> hotel;

