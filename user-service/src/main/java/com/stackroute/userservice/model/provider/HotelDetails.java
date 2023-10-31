package com.stackroute.userservice.model.provider;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "hotelInfo")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class HotelDetails {
	
	@Id
	private String hotelAddress;
	
	private String hotelName;
	private String hotelOwnerName;
	
	private double roomCostPerDay;
	private String summary;
}
