package com.stackroute.supplierservice.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.supplierservice.model.Flight;


@Repository
public interface FlightSearchRepository extends MongoRepository<Flight,Integer>{
	
	@Query("{'departureAirportName': :#{#departureAirportName},'arrivalAirportName': :#{#arrivalAirportName},'departureTime': : #{#departureTime}}")
	List<Flight> findByDepartureAirportNameArrivalAirportNameAndDepartureTime( String departureAirportName,
			String arrivalAirportName, LocalDate departureTime);

	List<Flight> findByDepartureAirportNameAndArrivalAirportName(String departureAirportName,String arrivalAirportName);
//	@Query("{'departureAirportName': :#{#departureAirportName},'arrivalAirportName': :#{#arrivalAirportName}}")
//	List<Flight> findByData(@Param("departureAirportName") String departureAirportName,
//							@Param("arrivalAirportName")String arrivalAirportName);
	
	@Query("{'flight.flightId':?0}")
	List<Flight> findByFlightId(@Param("flightId") int flightId);
	List<Flight> findAllByEmailId(String emailId);
}

//	@Query("{'departureAirportName': :#{#departueAirportName},'arrivalAirportName': :#{#arrivalAirportName},'departureTime': :#{#departureTime}}")
//	List<Flight> findByData(@Param("departureAirportName") String departureAirportName,
//							@Param("arrivalAirportName")String arrivalAirportName,@Param("departureTime")String departureTime);
