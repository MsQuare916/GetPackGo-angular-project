package com.stackroute.userservice.model.provider;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "trainInfo")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class TrainDetails {
	
	@Id
	private String trainId;
	
	private String trainName;
	private String departurePlatformName;
	private Date departureTime;
	private Map<String, Date> trainJourneyDetails;
	private String arrivalPlatformName;
	private Date arrivalTime;
	private double trainCost;
}
