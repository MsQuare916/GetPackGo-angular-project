package com.stackroute.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.userservice.model.provider.Provider;

@Repository

public interface ProviderRepository extends MongoRepository<Provider, String>{

}
