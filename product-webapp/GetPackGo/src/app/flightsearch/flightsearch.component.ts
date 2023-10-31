import { RazorpayService } from './../razorpay.service';
import { IFlight, Flights, Booking, ServiceType, Trains, Buses, Hotels, Cabs } from './../model';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


declare let Razorpay: any;
@Component({
  selector: 'app-flightsearch',
  templateUrl: './flightsearch.component.html',
  styleUrls: ['./flightsearch.component.css']
})


export class FlightsearchComponent implements OnInit {

  searchFlights : any[] = [];
  public result = [];

  selectDate : Date;
  selectedDate:string;
  servicetype: ServiceType;
  constructor( private _service : DataService, private activatedRouter:ActivatedRoute, private razorPayService: RazorpayService) {
    
   }

   RAZORPAY_OPTIONS = {
    key: 'rzp_test_WQNKvWQvERoXK4',
    amount: '',
    name: 'Razorpay',
    order_id: '',
    description: 'Load Wallet',
    prefill: {
      name: 'Rohith',
      email: 'rohith@gmail.com',
      contact: '9876543201',
    },
    modal: {},
    theme: {
      color: '#0096C5',
    },
  };


  ngOnInit(): void { 
    this._service.getSearchFlights(this.activatedRouter.snapshot.paramMap.get('fdepname') as string,
    this.activatedRouter.snapshot.paramMap.get('farrname') as string,this.activatedRouter.snapshot.paramMap.get('fdate')as string)
    .subscribe(data =>{
      this.searchFlights=data
    })
  }
  public flight:IFlight;
  public flights : Flights={
    flightName: '',
    departure_date: undefined,
    booking_date: undefined,
    flight_id: 0,
    departure_location: '',
    arrival_location: '',
    departure_time: '',
    arrival_time: ''
  };
  public booking : Booking = {
    bookingId: '',
    userEmailId: '',
    providerEmailId: '',
    serviceType: ServiceType.Flight,
    flights: new Flights,
    trains: new Trains,
    bus: new Buses,
    hotels: new Hotels,
    cabs: new Cabs,
    booking_cancelled: false,
    checked_in: false,
    age: 0,
    name: ''
  };
  bookNow(data:any){
    // this.flight=data;
    this.flights.flight_id=data.flightId;
    this.flights.flightName=data.flightName;
    this.flights.departure_location=data.departureAirportName;
    this.flights.departure_date=data.departureTime;
    this.flights.arrival_location=data.arrivalAirportName;
    this.flights.arrival_time=data.arrivalTime;
    // this.flights.booking_date=Date.now();
    console.log("working");
    this.booking.flights=this.flights;
    this.booking.userEmailId=sessionStorage.getItem("emailId");
    this.booking.providerEmailId="serviceprovider45@gmail.com";
    this.booking.serviceType=ServiceType.Flight;
    this.booking.flights=this.flights;
    this.booking.age=22;
    this.booking.name="User Prem"
    
    this._service.updateBooking(this.booking).subscribe((response)=>{
      
      
    },error =>{
      // alert("inside error");
      let order={
        "paymentId" : "",
        "orderId" : "",
        "userEmailId" : "rohith@123",
        "totalPrice" : data.flightCost,
        "status" : "passed",
        "razorpayOrderId" : " "
       }
      this.razorPayService.createOrder(order).subscribe(resp=>{
        console.log(resp.razorpayOrderId);
        this.RAZORPAY_OPTIONS.order_id=resp.razorpayOrderId;
        this.RAZORPAY_OPTIONS.amount = 100 + '00';
  
      // binding this object to both success and dismiss handler
      this.RAZORPAY_OPTIONS['handler'] = this.razorPaySuccessHandler.bind(this);
  
      // this.showPopup();
  
      let razorpay = new Razorpay(this.RAZORPAY_OPTIONS);
      razorpay.open();
      })
    })

  }
  public razorPaySuccessHandler(response) {
    console.log(response);
    // this.razorpayResponse = `Payment Successful`;
    // this.showModal = true;
    // this.cd.detectChanges();
    document.getElementById('razorpay-response').style.display = 'block';
  }
   
}












//   public colors = [
//     {
//         "flightId": 101,
//         "flightName": "AirIndia",
//         "departueAirportName": "Hyderabad",
//         "departureTime": "13/12/2022",
//         "arrivalAirportName": "Mumbai",
//         "arrivalTime": "14/12/2022",
//         "flightTime": "14/12/2022",
//         "flightCost": 9500.0
//     },
//     {
//         "flightId": 102,
//         "flightName": "Indigo",
//         "departueAirportName": "Hyderabad",
//         "departureTime": "14/12/2022",
//         "arrivalAirportName": "Vishakapatanam",
//         "arrivalTime": "15/12/2022",
//         "flightTime": "15/12/2022",
//         "flightCost": 6000.0
//     }
// ];
