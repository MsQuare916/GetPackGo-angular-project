package com.stackroute.userservice.model.provider;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "FlightInfo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FlightDetalis {
	
	@Id
	private String flightId;
	
	private String flightName;
	private String departueAirportName;
	private Date departureTime;
	private String arrivalAirportName;
	private Date arrivalTime;
	private Date flightTime;
	private double flightCost;
}
