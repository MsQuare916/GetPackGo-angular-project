export class IFlight{
    
    flightId : number;
	flightName : string;
	departureAirportName : string;
	departureTime : Date;
	arrivalAirportName : string;
	arrivalTime : any;
	flightTime : any;
	flightCost : number;
	emailId : string;
}

export class Flights {
   
	flightName : string;
   
	departure_date : Date;
	booking_date : Date;
	flight_id : number;
	departure_location : string;
	arrival_location : string;
	departure_time : string;
	arrival_time : string;
	
}

export class Hotel{
	hotelId : number;
    hotelAddress : string;
	hotelName : string;
	hotelOwnerName : string;
	roomCostPerDay : number;
	summary : string;
}
export class Hotels {
	
	hotelId : number;
	hotel_name : string;
	location : string;
	Check_In : Date;
	roomNo: number;
	cost : number;
	
}
export class Train{
	 
	trainId : number;
	trainName : string;
	departurePlatformName :string;
	departureTime : string ;
	// trainJourneyDetails any Map<String, String>;
	trainJourneyDetails : Map<string,string>;
	arrivalPlatformName : string;
	arrivalTime : string;
	trainCost : number;
}

export class Trains  {
	trainName : string;
	departure_date : Date;
	booking_date : Date;
	departure_location: string;
	arrival_location : string;
	departure_time : string;
	arrival_time : string;
	trainNo : number;
	
}

export class Bus {
	busId : number;
	busName : string ;
	departureStation : string;
	departureTime : string;
	busJourneyDetails : Map<string,string>;
	arrivalStation :string;
	arrivalTime : string;
	cost : number;
}

export class Buses {
	busName : string;
	departure_date : Date;
	booking_date : Date;
	busNo : number;
	departure_location : string;
	arrival_location : string;
	departure_time : string;
	arrival_time : string;
	
	}

export class User{
	// constructor(){}
	primaryMail: string;
	firstName: string;
	middleName: string;
	lastName: string;
	secondaryMail: string;
	address: string;
	mobileNo1: string;
	mobileNo2: string;
	preferedLanguage: any;
	panCardNo: string;
}

export class Provider{
	primaryMail: string;
	firstName: string;
	middleName: string;
	lastName: string;
	secondaryMail: string;
	address: string;
	mobileNo1: string;
	mobileNo2: string;
	preferedLanguage: any;
	panCardNo: string;
	aadharCardNo: string;
}
export enum ServiceType {
	Flight,
	Cab,
	Bus,
	Hotel,
	Trains
}

export class Cabs {


	cabId: number;
	cab_name:string;
	cab_type:string;
	Fuel_type:string;
	pickup_date:Date;
	start_location:string;
	destiny_location:string;
	pickup_time:string;
	isBooking_cancelled:boolean;
	
	
}
export class Booking {

   bookingId : string;
   userEmailId :string;
   providerEmailId: string ;
	serviceType : ServiceType;
   flights : Flights;
   trains : Trains;
   bus : Buses;
    hotels : Hotels;
   cabs : Cabs;

   booking_cancelled : boolean = false;
   checked_in : boolean= false;
   age : number;
   name : string;
   
}