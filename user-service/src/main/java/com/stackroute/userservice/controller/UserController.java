package com.stackroute.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.userservice.exceptions.UserAlreadyExistException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.user.User;
import com.stackroute.userservice.service.UserServiceImpl;

@RestController
@ComponentScan(basePackages = {"com.stackroute.userservice"})
@RequestMapping("/api/v1")
//@CrossOrigin
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	//get user using user name
	@GetMapping("/user/{emailId}")
	public ResponseEntity<Object> getUserByEmailId(@PathVariable String emailId) {
		
		try {
			User userByUserName = userServiceImpl.getUserByEmailId(emailId);
			if(userByUserName != null) {
				logger.info("In controller - {}", "User Profile retrieved for Email Id: "+emailId+ " is: "+userByUserName);
				return new ResponseEntity<>(userByUserName, HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {
			logger.info("In controller - {}", "Email Id"+ emailId + " not found in database.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		logger.info("In controller - {}", "Email Id "+ emailId + " not found in database.");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//get all users present
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userServiceImpl.findAllUserAsList();
	}
	
	//returns user profile pic
//	@GetMapping("/user/profile/pic")
//	public String getProfilePicByUserName() {
//		try {
//		String str = new ClassPathResource("/image/user/").getFile().getAbsolutePath();
//		System.out.println(str);
//		return "no";
//		}
//		catch(Exception e) {
//		return "e";
//		}
//	}
	
	//delete user with user name
	@DeleteMapping("user/delete/{emailId}")
	public ResponseEntity<String> deleteUser(@PathVariable String emailId) {
		try {
			String userDeleted = userServiceImpl.deleteUserByEmailId(emailId);
			logger.info("In controller - {}", "User profile deleted for email id: " + emailId); 
			return new ResponseEntity<>(userDeleted, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			logger.info("In controller - {}", "Email Id " + emailId + " doesnt exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//add user details
	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody User user){
		System.out.println("controller is called");
		try {
			String userRegistration = userServiceImpl.addUser(user);
			logger.info("In controller - {}", "User Profile created: " + user);
			return new ResponseEntity<>(userRegistration, HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			logger.info("In controller - {}", "Email Id" + user.getPrimaryMail() + " already exists.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "User Name " + user.getPrimaryMail() + " already exists.");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//add profile pic to username
	//profile pic will be stored in target/classes/static/image/user/ with there username with extention .jpg
	@PostMapping("/user/pic/{emailId}")
	public ResponseEntity<Object> addProfilePic(@RequestParam("image") MultipartFile profilePic, @PathVariable("emailId") String emailId){
		
		try {
			String userProfilePic = userServiceImpl.addProfilePicLocation(profilePic , emailId);
			logger.info("In controller - {}", "User profilepic is created.");
			return new ResponseEntity<>(userProfilePic, HttpStatus.OK);
		} catch (UserAlreadyExistException e) {
			logger.info("In controller - {}", "failed to add profile pic");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		/*try {
			String userProfilePic = userServiceImpl.addProfilePic(userName, profilePic);
			logger.info("In controller - {}", "User Profile Pic created: " + profilePic);
			return new ResponseEntity<>(userProfilePic, HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			logger.info("In controller - {}", "failed to add profile pic");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	}
	
	
	//update user using username
	@PutMapping("/user/save")
	public ResponseEntity<Object> saveOrUpdateUser(@RequestBody User user){
		try {
			String userSaved = userServiceImpl.saveOrUpdateUser(user);
			logger.info("In controller - {}", "User profile updated: " + user);
			return new ResponseEntity<>(userSaved, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			logger.info("In controller - {}", "Email Id  " + user.getPrimaryMail(), " not found in database");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//update profile pic using username
	@PutMapping("user/save/pic/{emailId}")
	public ResponseEntity<Object> saveOrUpdateProfilePic(@PathVariable("emailId") String emailId, @RequestParam("image") MultipartFile profilePic){
		try {
			String userProfilePicUpdate = userServiceImpl.saveOrUpdateProfilePic(profilePic, emailId);
			logger.info("In controller - {}", "User profilepic updated.");
			return new ResponseEntity<>(userProfilePicUpdate, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			logger.info("In controller - {}", "email Id " + emailId + " notfound in database");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		/*try {
			String userProfilePicUpdate = userServiceImpl.saveOrUpdateProfilePic(userName, profilePic);
			logger.info("In controller - {}", "User profile updated: " + profilePic);
			return new ResponseEntity<>(userProfilePicUpdate, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			logger.info("In controller - {}", "User Name " + userName + " not found in database");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	}
	

//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}
//	
	
}
