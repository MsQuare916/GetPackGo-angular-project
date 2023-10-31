package com.stackroute.supplierservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.supplierservice.model.SupplierInfo;

public interface SupplierRepository extends MongoRepository<SupplierInfo,String>{
	
}
