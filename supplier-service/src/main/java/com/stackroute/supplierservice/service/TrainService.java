package com.stackroute.supplierservice.service;

import java.util.List;

import com.stackroute.supplierservice.exceptions.TrainAlreadyExistsException;
import com.stackroute.supplierservice.exceptions.TrainNotFoundException;
import com.stackroute.supplierservice.model.Train;

public interface TrainService {
	public Train getTrainById(int trainId) throws TrainNotFoundException;
	public String deleteTrainById(int trainId) throws TrainNotFoundException;
	public List<Train> findAllTrainAsList();
	public String addTrain(Train train) throws TrainAlreadyExistsException;
	public String updateTrain(Train train) throws TrainNotFoundException;
	List<Train> findAllTrainByEmailId(String emailId);
}
