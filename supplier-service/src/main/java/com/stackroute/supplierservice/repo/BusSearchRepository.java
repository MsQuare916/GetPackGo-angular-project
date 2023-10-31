package com.stackroute.supplierservice.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.supplierservice.model.Bus;

@Repository
public interface BusSearchRepository extends MongoRepository<Bus,Integer>{
	
	@Query("{'departureStation': :#{#departureStation},'arrivalStation': :#{#arrivalStation},'departureTime': : #{#departureTime}}")
	List<Bus > findByDepartureStationArrivalStationAndDepartureTime(@Param("departureStation") String departureStation,
						  @Param("arrivalStation")String arrivalStation,
						  @Param("departureTime")LocalDate departureTime);

	List<Bus> findByDepartureStationAndArrivalStation(String departureStation,
													  String arrivalStation);
	
	@Query("{'bus.busId':?0}")
	List<Bus> findByBus_Id(@Param("busId") int busId);
	List<Bus> findAllByEmailId(String emailId);
}
