package com.stackroute.authenticationservice.Datamodel;


import lombok.AllArgsConstructor;



import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserModel{

	@Id
	private String email;
	private String password;
	private Role role;


}
