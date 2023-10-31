package com.stackroute.supplierservice.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.supplierservice.model.Train;

@Repository
public interface TrainSearchRepository extends MongoRepository<Train, Integer>{
	

	@Query("{'departurePlatformName': :#{#departurePlatformName},'arrivalPlatformName': :#{#arrivalPlatformName},'departureTime': : #{#departureTime}}")
	List<Train> findByDeparturePlatformNameArrivalPlatformNameAndDepartureTime(String departurePlatformName,
						   String arrivalPlatformName,LocalDate departureTime);

	List<Train> findByDeparturePlatformNameAndArrivalPlatformName(String departurePlatformName,String arrivalPlatformName);
	
	@Query("{'train.trainId':?0}")
	List<Train> findByTrainId(@Param("trainId") int trainId);
	List<Train> findAllByEmailId(String emailId);
}
