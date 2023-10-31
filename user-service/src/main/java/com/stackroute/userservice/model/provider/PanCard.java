package com.stackroute.userservice.model.provider;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PanCard {
	
	private String panNo;
	private String panName;
	private String fatherName;
	private Date panDOB;
	
}
