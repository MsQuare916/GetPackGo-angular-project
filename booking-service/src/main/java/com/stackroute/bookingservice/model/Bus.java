
package com.stackroute.bookingservice.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

	private String busName;
	   
	private LocalDate departure_date;
	private LocalDate booking_date;
	private int busNo;
	private String departure_location;
	private String arrival_location;
	private String departure_time;
	private String arrival_time;
	
	}