package com.stackroute.userservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stackroute.userservice.exceptions.UserAlreadyExistException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.user.User;

public interface UserService {
	public User getUserByEmailId(String emailId) throws UserNotFoundException;
	public String deleteUserByEmailId(String emailId) throws UserNotFoundException;
	public String addUser(User user) throws UserAlreadyExistException;
	public List<User> findAllUserAsList();
	public String saveOrUpdateUser(User user) throws UserNotFoundException;
	public String addProfilePicLocation(MultipartFile profilePic, String emailId) throws UserAlreadyExistException, IOException;
	public String saveOrUpdateProfilePic(MultipartFile profilePic, String emailId) throws UserNotFoundException, IOException;
	
}
