import { Booking, Buses, Cabs, Flights, Hotels, ServiceType, Train } from './../model';
import { ActivatedRoute } from '@angular/router';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Trains } from '../model';

@Component({
  selector: 'app-trainsearch',
  templateUrl: './trainsearch.component.html',
  styleUrls: ['./trainsearch.component.css']
})
export class TrainsearchComponent implements OnInit {

  constructor(private _service : DataService,private activatedRouter : ActivatedRoute) {
    
   }
   public searchTrains = [];

  ngOnInit(): void {
    this._service.getSearchTrains(this.activatedRouter.snapshot.paramMap.get('tdepname') as string,this.activatedRouter.snapshot.paramMap.get('tarrname') as string,this.activatedRouter.snapshot.paramMap.get('tdate') as string)
    .subscribe(data =>{
      this.searchTrains = data;
    })
  }
  public booking : Booking={
    bookingId: '',
    userEmailId: '',
    providerEmailId: '',
    serviceType: ServiceType.Trains,
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
  public trains : Trains = {
    trainName: '',
    departure_date: undefined,
    booking_date: undefined,
    departure_location: '',
    arrival_location: '',
    departure_time: '',
    arrival_time: '',
    trainNo: 0
  };
  bookNow(data:any){
   
    this.trains.trainNo = data.trainId;
    this.trains.trainName = data.trainName;
    this.trains.departure_location = data.departurePlatformName;
    this.trains.departure_date = data.departureTime;
    this.trains.arrival_location = data.arrivalPlatformName;
    this.trains.arrival_time = data.arrivalTime;

    console.log("working for trains");
    this.booking.userEmailId="user123@gmail.com";
    this.booking.providerEmailId="serviceprovider45@gmail.com";
    this.booking.serviceType=ServiceType.Trains;
    this.booking.trains=this.trains;
    this.booking.age=22;
    this.booking.name="User Prem"
    
    this._service.updateBooking(this.booking).subscribe((response:any)=>{
      console.log(response);
    })

  }

}
