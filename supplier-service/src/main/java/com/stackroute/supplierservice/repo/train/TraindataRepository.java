package com.stackroute.supplierservice.repo.train;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.stackroute.supplierservice.model.train.TrainData;

public interface TraindataRepository extends MongoRepository<TrainData, Integer>{

}
