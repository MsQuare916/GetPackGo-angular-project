package com.stackroute.bookingservice.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cabs {


	private int cabId;
	
	public String cab_name;
	private String cab_type;
	protected String Fuel_type;
	private Date pickup_date;
	private String start_location;
	private String destiny_location;
	private String pickup_time;
	public boolean isBooking_cancelled;
	
	
}
