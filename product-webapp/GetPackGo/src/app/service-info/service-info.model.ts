export class FlightData{
flightId:number;
flightName:string='';
departureAirportName:string='';
departureTime:string='';
startTime:string='';
arrivalAirportName:string='';
arrivalTime:string='';
endTime:string='';
// flightTime:string='';
flightCost:number;
emailId:string='';
}

export class HotelData{
hotelId:number;
hotelAddress:string='';
hostelName:string='';
hostelOwnerName:string='';
roomCostPerDay:number;
summary:string='';
emailId:string='';
}

export class TrainData{
    trainId:number;
    trainName:string='';
    departurePlatformName:string='';
    departureTime:string='';
    trainJourneyDetails:string='';
    arrivalPlatformName:string='';
    arrivalTime:string='';
    trainCost:number;
    emailId:string='';
}

export class BusData{
    busId:number;
    busName:string='';
    departureStation:string='';
    departureTime:string='';
    // busJourneyDetails:string='';
    arrivalStation:string='';
    arrivalTime:string='';
    cost:number;
    emailId:string='';
}


