package com.stackroute.bookingservice.model;



import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	@Id
   public String bookingId;
	
   private String userEmailId;
   private String providerEmailId;
   private ServiceType serviceType;
   private Flight flights;
   private Trains trains;
   private Bus bus;
   private Hotels hotels;
   private Cabs cabs;

   
   @Field
   public boolean booking_cancelled= false;
   public boolean checked_in= false;
   
   private int age;
   private String name;

}