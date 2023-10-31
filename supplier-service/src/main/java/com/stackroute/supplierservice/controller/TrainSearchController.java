package com.stackroute.supplierservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.stackroute.supplierservice.repo.TrainSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.supplierservice.exceptions.TrainAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.TrainNotFoundException;
import com.stackroute.supplierservice.model.Train;
import com.stackroute.supplierservice.serviceimpl.TrainServiceImpl;

@RestController
@ComponentScan (basePackages = "com.stackroute.supplierservice")
@RequestMapping("api/v1")
public class TrainSearchController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public TrainServiceImpl trainServiceImpl;

	@Autowired
	public TrainSearchRepository trepo;
	
	@PostMapping("/train")
	public ResponseEntity<String> addTrain(@RequestBody Train train) {
		
	try {
		String result = trainServiceImpl.addTrain(train);
		logger.info("In controller - {}", " Train added: " + train );
		return new ResponseEntity<>(result, HttpStatus.OK);
			} catch(TrainAlreadyExistsException e) {
		logger.info("In controller - {}", "trainId : " + train.getTrainId() + " already exists.");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
			} catch (Exception e) {
		logger.info("In controller - {}", "Something went wrong");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@GetMapping("train/{trainId}")
	public ResponseEntity<Object> getTrainById(@PathVariable("trainId") int trainId) {
		try {
			Train trainById = trainServiceImpl.getTrainById(trainId);
			if(trainById != null) {
				logger.info("In controller - {}", "Found Train with Id : " + trainId + " is: " + trainById);
				return new ResponseEntity<>(trainById, HttpStatus.OK);
			}
		} catch (TrainNotFoundException e){
			logger.info("In controller - {}", "Train with Id : " + trainId + " not fouund.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		logger.info("In controller - {}", "Id: " + trainId + " not found in database");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/trains")
	public List<Train> getAllTrain(){

		return trainServiceImpl.findAllTrainAsList();

	}

		@GetMapping("/train/{departurePlatformName}/{arrivalPlatformName}/{departureTime}")
		public ResponseEntity<Object> getTrainByData(@PathVariable("departurePlatformName") String departurePlatformName,
										   @PathVariable("arrivalPlatformName") String arrivalPlatformName,
										   @PathVariable("departureTime") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate departureTime){
		try{
			List<Train> trainlist = new ArrayList<>();
			for(Train train : trepo.findByDeparturePlatformNameAndArrivalPlatformName(departurePlatformName, arrivalPlatformName)){
				if(train.getDepartureTime().isEqual(departureTime)){
					trainlist.add(train);
				}
			}
			return new ResponseEntity<>(trainlist, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/train/update")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train){
		
		try{
			trainServiceImpl.updateTrain(train);
			logger.info("In controller - {}","trainId : "+train.getTrainId()+" Train is updated");
			return new ResponseEntity<>(train,HttpStatus.OK);
		} catch (TrainNotFoundException e) {
			logger.info("In controller -{} trainId :"+train.getTrainId()+" doesn't exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.info("In controller -{} ","Something went wrong");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/train/delete/{id}")
	public ResponseEntity<String> deleteTrainById(@PathVariable("id") int trainId){
		try{
			String result = trainServiceImpl.deleteTrainById(trainId);
			logger.info("In controller - {}", "trainId :"+trainId+" Bus is deleted successfully");
			return new ResponseEntity<>(result, HttpStatus.OK);
			} catch(TrainNotFoundException e) {
				logger.info("In controller -{} trainId :"+trainId+" bus doesn't exists");
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			} catch (Exception e) {
				logger.info("In controller -{} ","Something went wrong");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	@GetMapping ("trains/{emailId}")
		public ResponseEntity<?> findTrainsByEmailId(@PathVariable String emailId){
		return new ResponseEntity<>(trainServiceImpl.findAllTrainByEmailId(emailId),HttpStatus.OK);
		}

//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}
//	
}

	
//	@Autowired
//	public TrainSearchRepository trepo;
//	@Autowired
//	public TraindataRepository tdrepo;
	
//	@GetMapping("/allTrains")
//	public List<Train> getAllTrains(){
//		return trepo.findAll();		
//	}
//	
//	//Getting list of Flight from location to destination
//	

//	
//	// For ServiceProvider to add new Flight
//	
//		@PostMapping("/addTrains")
//		public String addTrain(@RequestBody Train train) {
//			trepo.save(train);
//		return "Added Train:" +train.getTrainId();
//		}
//		
//		@PostMapping("/addTrainData")
//		public String addTrainData(@RequestBody TrainData train) {
//			tdrepo.save(train);
//			return "Added Train: "+train.getTrain_id();
//		
//		}
//		
//		@PutMapping("/updateTrainsData/{id}")
//		public TrainData updateTrain(@RequestBody TrainData train,@PathVariable("id") int train_id){
//			train.setTrain_id(train_id);
//			tdrepo.save(train);
//			return train;
//			
//		}

