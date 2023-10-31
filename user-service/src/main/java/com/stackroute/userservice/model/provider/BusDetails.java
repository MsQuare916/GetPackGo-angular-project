package com.stackroute.userservice.model.provider;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "BusInfo")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class BusDetails {
	
	@Id
	private String busId;
	
	private String busName;
	private String departureStation;
	private Date depatureTime;
	private Map<String, Date> busJourneyDetails;
	private String arrivalStation;
	private Date arrivalTime;
	private double cost;
}
