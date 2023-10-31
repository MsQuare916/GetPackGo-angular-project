package com.stackroute.userservice.model.provider;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.userservice.model.user.AadharCard;
import com.stackroute.userservice.model.user.PreferedLanguage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "providerInfo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Provider {
	
	@Id
	private String primaryMail;
	
	
	private String userName;
	private String profilePic;
	private String firstName;
	private String middleName;
	private String lastName;
	private String secondaryMail;
	private String address;
	private AadharCard aadharCard;
	private PanCard panCard;
	private String mobileNo1;
	private String mobileNo2;
	private PreferedLanguage preferedLanguage;
	//private String answer1;
	//private String answer2;
	//private String answer3;
	private TypeOfService typeOfService;
	private List<BusDetails> busServiceInfomation;
	private List<FlightDetalis> flightServiceInfomation;
	private List<HotelDetails> hotelServiceInformation;
	private List<TrainDetails> trainServiceInformation;
	
}
