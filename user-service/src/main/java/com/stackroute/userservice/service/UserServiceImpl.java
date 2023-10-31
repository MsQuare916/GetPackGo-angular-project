package com.stackroute.userservice.service;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.userservice.exceptions.UserAlreadyExistException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.user.User;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserByEmailId(String emailId) throws UserNotFoundException{
		if(userRepository.existsById(emailId)) {
			return userRepository.findById(emailId).get();
		}
		throw new UserNotFoundException();
	}

	@Override
	public String deleteUserByEmailId(String emailId) throws UserNotFoundException{
		if (userRepository.existsById(emailId)) {
			userRepository.deleteById(emailId);
			return "Sucessfull!!";
		}
		throw new UserNotFoundException();
	}

	@Override
	public String addUser(User user) throws UserAlreadyExistException{
		if (!userRepository.existsById(user.getPrimaryMail())) {
			userRepository.save(user);
			return "Successfully added user!!";
		}
		throw new UserAlreadyExistException();
	}

	@Override
	public List<User> findAllUserAsList() {
		return userRepository.findAll();
	}

	@Override
	public String saveOrUpdateUser(User user) throws UserNotFoundException{
		if (userRepository.existsById(user.getPrimaryMail())) {
			userRepository.save(user);
			return "Successfully updated the changes!!";
		}
		throw new UserNotFoundException();
	}

	@Override
	public String addProfilePicLocation(MultipartFile profilePic, String emailId) 
			throws UserAlreadyExistException, IOException{
		if (userRepository.existsById(emailId)) {
			User user = userRepository.findById(emailId).get();
			if (user.getProfilePic() != null) {
				user.setProfilePic(new String(Base64.encodeBase64(profilePic.getBytes())));
				userRepository.save(user);
				return "Successfully added photo to profile";
			}
		}
		throw new UserAlreadyExistException();
	}

	@Override
	public String saveOrUpdateProfilePic(MultipartFile profilePic, String emailId)
			throws UserNotFoundException, IOException {
		if (userRepository.existsById(emailId)) {
			User user = userRepository.findById(emailId).get();
			user.setProfilePic(new String(Base64.encodeBase64(profilePic.getBytes())));
			userRepository.save(user);
			return "Successfully updated the profile photo";
		}
		throw new UserNotFoundException();
	}
}
