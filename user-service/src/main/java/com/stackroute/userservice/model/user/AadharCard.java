package com.stackroute.userservice.model.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AadharCard {
	
	private String aadharNo;
	private String aadharName;
	private Gender sex;
	private Date birthDate;
	private String fatherName;
	private String address;
		
}
