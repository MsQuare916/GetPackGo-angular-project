package com.stackroute.supplierservice.serviceimpl;

import java.util.List;

import com.stackroute.supplierservice.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.supplierservice.exceptions.TrainAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.TrainNotFoundException;
import com.stackroute.supplierservice.model.Train;
import com.stackroute.supplierservice.repo.TrainSearchRepository;
import com.stackroute.supplierservice.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService{
	
	@Autowired
	TrainSearchRepository trainRepo;
	
	@Override
	public String addTrain(Train train) throws TrainAlreadyExistsException {
		if(!trainRepo.existsById(train.getTrainId())) {
			trainRepo.save(train);
			return "Bus with Id"+train.getTrainId()+"added successfully";
		}
		throw new TrainAlreadyExistsException();
	}

	@Override
	public String updateTrain(Train train) throws TrainNotFoundException {
		if(trainRepo.existsById(train.getTrainId())) {
			trainRepo.save(train);
			return "Successfully updated the changes!!";
		}
		throw new TrainNotFoundException();
	}

	@Override
	public List<Train> findAllTrainByEmailId(String emailId) {
		return trainRepo.findAllByEmailId(emailId);
	}

	@Override
	public Train getTrainById(int trainId) throws TrainNotFoundException {
		if(trainRepo.existsById(trainId)) {
			return trainRepo.findById(trainId).get();
		}
		throw new TrainNotFoundException();
	}

	@Override
	public String deleteTrainById(int trainId) throws TrainNotFoundException {
		if(trainRepo.existsById(trainId)) {
			trainRepo.deleteById(trainId);
			return "Train with Id : "+trainId+" deleted successfully";
		}
		throw new TrainNotFoundException();
	}

	@Override
	public List<Train> findAllTrainAsList() {
		return trainRepo.findAll();
	}


	}

