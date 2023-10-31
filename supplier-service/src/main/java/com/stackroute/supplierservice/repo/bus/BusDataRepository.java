package com.stackroute.supplierservice.repo.bus;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.stackroute.supplierservice.model.bus.BusData;

public interface BusDataRepository extends MongoRepository<BusData,Integer>{
	
}
