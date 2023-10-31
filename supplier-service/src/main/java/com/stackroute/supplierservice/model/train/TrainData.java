package com.stackroute.supplierservice.model.train;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Document(collection  = "trainDataInfo")
@Data
public class TrainData {
	
	@Id
	private int train_id;

	private String departure_time;
	private String arrival_time;
	private double cost;
}
