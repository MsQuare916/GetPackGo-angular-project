
package com.stackroute.bookingservice.model;

import java.time.LocalDate;
import java.util.Date;

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
public class Hotels {

	
	private int hotelId;
	
	private String hotel_name;
	private String location;
	private Date Check_In;
	private int roomNo;
	private int cost;
	
}