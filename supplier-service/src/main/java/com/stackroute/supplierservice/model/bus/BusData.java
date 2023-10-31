package com.stackroute.supplierservice.model.bus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection  = "busDataInfo")

@Data
public class BusData{
	@Id
	private int bus_id;
	
//	private Date startDate;
//	private Date endDate;
	private String departure_time;
	private String arrival_time;
	private double cost;
	
}
