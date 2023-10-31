package com.stackroute.userservice.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "userInfo")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User {
	
	@Id
	private String primaryMail;
	
	private String profilePic;
	private String firstName;
	private String middleName;
	private String lastName;
	private String secondaryMail;
	private String address;
	//private AadharCard aadharCard;
	private String mobileNo1;
	private String mobileNo2;
	private PreferedLanguage preferedLanguage;
	private String panCardNo;
	private String gender;
	//private String answer1;
//	private String answer2;
//	private String answer3;

}
