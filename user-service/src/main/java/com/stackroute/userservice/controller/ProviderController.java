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

import com.stackroute.userservice.exceptions.ProviderAlreadyExistsException;
import com.stackroute.userservice.exceptions.ProviderNotFoundException;
import com.stackroute.userservice.model.provider.Provider;
import com.stackroute.userservice.service.ProviderServiceImpl;

@RestController
@ComponentScan(basePackages = {"com.stackroute.userservice"})
@RequestMapping("/api/v1")
//@CrossOrigin
public class ProviderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProviderServiceImpl providerServiceImpl;
	
	//get provider using username
	@GetMapping("/provider/{emailId}")
	public ResponseEntity<Object> getProviderByUserName(@PathVariable String emailId){
		try {
			Provider providerByUserName = providerServiceImpl.getProviderByEmailId(emailId);
			if (providerByUserName != null) {
				logger.info("In controller - {}", "Provider profile retrieved for emailId: " + emailId + " is: " + providerByUserName);
				return new ResponseEntity<>(providerByUserName, HttpStatus.OK);
			}
		} catch(ProviderNotFoundException e) {
			logger.info("In controller - {}", "Email Id " + emailId + " not found in database");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		logger.info("In controller - {}", "Something went wrong");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//get all provider present
	@GetMapping("/providers")
	public List<Provider> getAllProviders(){
		return providerServiceImpl.findAllProviderAsList();
	}
	
	//delete provider with username
	@DeleteMapping("provider/delete/{emailId}")
	public ResponseEntity<String> deleteProvider(@PathVariable String emailId){
		try {
			String providerDeleted = providerServiceImpl.deleteProviderByEmailId(emailId);
			logger.info("In controller - {}", "Provider profile deleted for the emailId: " + emailId);
			return new ResponseEntity<>(providerDeleted, HttpStatus.OK);
		} catch(ProviderNotFoundException e) {
			logger.info("In controller - {}", "Email Id " + emailId + " doesn't exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//add provider details
	@PostMapping("/provider")
	public ResponseEntity<Object> addProvider(@RequestBody Provider provider){
		try {
			String providerRegistration = providerServiceImpl.addProvider(provider);
			logger.info("In controller - {}", "Provider profile created: " + provider);
			return new ResponseEntity<>(providerRegistration, HttpStatus.OK);
		} catch(ProviderAlreadyExistsException e) {
			logger.info("In controller - {}", "Email Id " + provider.getPrimaryMail() + " already exists.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//add profile pic to username
	//profile pic will be stored in target/classes/static/image/provider/ with there username with extention .jpg
	@PostMapping("/provider/pic/{emailId}")
	public ResponseEntity<Object> addProfilePic(@RequestParam MultipartFile profilePic, @PathVariable("emailId") String emailId){
		try {
			String providerProfilePic = providerServiceImpl.addProfilePicLocation(profilePic, emailId);
			logger.info("In controller - {}", "User profilepic is created");
			return new ResponseEntity<>(providerProfilePic, HttpStatus.OK);
		} catch(ProviderAlreadyExistsException e) {
			logger.info("In controller - {}", "failed to add profile pic");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		/*try {
			String providerProfilePic = providerServiceImpl.addProfilePic(userName, profilePic);
			logger.info("In controller - {}", "User Profile Pic created: " + profilePic);
			return new ResponseEntity<>(providerProfilePic, HttpStatus.CREATED);
		} catch (ProviderAlreadyExistsException e) {
			logger.info("In controller - {}", "failed to add profile pic");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	}
	
	//update provider using username
	@PutMapping("/provider/save")
	public ResponseEntity<Object> saveOrUpdateProvider(@RequestBody Provider provider){
		try {
			String providerSaved = providerServiceImpl.saveOrUpdateProvider(provider);
			logger.info("In controller - {}", "Provider profile updated: " + provider);
			return new ResponseEntity<>(providerSaved, HttpStatus.OK);
		} catch (ProviderNotFoundException e) {
			logger.info("In controller - {}", "Email Id " + provider.getPrimaryMail(), " not found in database");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//update profile pic using username
	@PutMapping("/provider/save/pic/{emailId}")
	public ResponseEntity<Object> saveOrUpdateProfilePic(@PathVariable("emailId") String emailId, @RequestBody MultipartFile profilePic){
		
		try {
			String providerProfilePicUpdate = providerServiceImpl.saveOrUpdateProfilePic(profilePic, emailId);
			logger.info("In controller - {}", "Provider profilepic updated");
			return new ResponseEntity<>(providerProfilePicUpdate, HttpStatus.OK);
		} catch(ProviderNotFoundException e) {
			logger.info("In controller - {}", "Email Id " + emailId + " notfound in database");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch(Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		/*try {
			String providerProfilePicUpdate = providerServiceImpl.saveOrUpdateProfilePic(userName, profilePic);
			logger.info("In controller - {}", "User profile updated: " + profilePic);
			return new ResponseEntity<>(providerProfilePicUpdate, HttpStatus.OK);
		} catch (ProviderNotFoundException e) {
			logger.info("In controller - {}", "Username " + userName + " not found in database");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller - {}", "Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	}
	
	
	 
//    @GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//    }
}
