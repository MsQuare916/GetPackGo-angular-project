package com.stackroute.bookingservice.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trains  {
	
	

	private String trainName;
	   
	private LocalDate departure_date;
	private LocalDate booking_date;
	private String departure_location;
	private String arrival_location;
	private String departure_time;
	private String arrival_time;
	private int trainNo;
	
}