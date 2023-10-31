package com.stackroute.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import com.stackroute.authenticationservice.Datamodel.UserModel;
import com.stackroute.authenticationservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exceptions.UserNotFoundException;
import com.stackroute.authenticationservice.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService{
	
	@Autowired
	private UserRepository userRepo;
    
    // service layer for authentication

	public UserModel loadByUserEmail(String email) throws UserNotFoundException {

		UserModel user= userRepo.findById(email).get();
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;

	}
	//service layer for adding the user details
	
	   public UserModel saveUser(UserModel user, String email) throws UserAlreadyExistsException {	
		   log.info(user.toString());
		   if (userRepo.existsById(user.getEmail())) {
				throw new UserAlreadyExistsException();
			}

			return userRepo.save(user);		
		}
	   
	//service layer for updating login details

	public UserModel updateUser(UserModel update) throws UserNotFoundException {

		UserModel savedModel = userRepo.findById(update.getEmail()).get();
		if (userRepo.existsById(update.getEmail())) {

			savedModel.setEmail(update.getEmail());
			savedModel.setPassword(update.getPassword());
			return userRepo.save(update);
		}
		throw new UserNotFoundException();
	}

}

