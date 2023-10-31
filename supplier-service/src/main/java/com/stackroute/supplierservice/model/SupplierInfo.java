package com.stackroute.supplierservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection="supplierInfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SupplierInfo {
	
	
	@Id
	private String serviceName;
	
	private String serviceProviderName;
	private String serviceAddress;
	private String serviceCity;
	private int servicePincode;
	private double serviceCost;
	
}
