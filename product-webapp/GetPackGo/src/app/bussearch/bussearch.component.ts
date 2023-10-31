import { ActivatedRoute } from '@angular/router';
import { Booking, Bus, Buses, Cabs, Flights, Hotels, ServiceType, Trains } from './../model';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscriber } from 'rxjs';

@Component({
  selector: 'app-bussearch',
  templateUrl: './bussearch.component.html',
  styleUrls: ['./bussearch.component.css']
})
export class BussearchComponent implements OnInit {

  

  constructor(private _service : DataService, private activatedRouter :ActivatedRoute ) {
    
   }
   busObj : Bus =new Bus;
   public searchBuses = [];
  //  onSubmit(form:any){
  //   this.busObj.departureStation = form.value.bdepname;
  //   this.busObj.arrivalStation = form.value.barrname;
  //   this._service.getSearchBuses(this.busObj.departureStation,this.busObj.arrivalStation)
  //   .subscribe(data => this.searchBuses = data);
  //   // this.searchBuses=this._service.getflightform();
  //   // console.log(form.value);
  //   form.reset();
  // }

  ngOnInit(): void {
    this._service.getSearchBuses(this.activatedRouter.snapshot.paramMap.get('bdepname') as string, 
    this.activatedRouter.snapshot.paramMap.get('barrname')as string,
    this.activatedRouter.snapshot.paramMap.get('bdate') as string)
    .subscribe(data =>{
      this.searchBuses = data
    })
  }

  public booking : Booking={
    bookingId: '',
    userEmailId: '',
    providerEmailId: '',
    serviceType: ServiceType.Bus,
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

  public buses: Buses={
    busName: '',
    departure_date: undefined,
    booking_date: undefined,
    busNo: 0,
    departure_location: '',
    arrival_location: '',
    departure_time: '',
    arrival_time: ''
  };

  bookNow(data:any){
   
    this.buses.busNo = data.busId;
    this.buses.busName = data.busName;
    this.buses.departure_location = data.departureStation;
    this.buses.departure_date = data.departureTime;
    this.buses.arrival_location = data.arrivalStation;
    this.buses.arrival_time = data.arrivalTime;

    console.log("working for buses");
    this.booking.userEmailId="user123@gmail.com";
    this.booking.providerEmailId="serviceprovider45@gmail.com";
    this.booking.serviceType=ServiceType.Bus;
    this.booking.bus=this.buses;
    this.booking.age=22;
    this.booking.name="User Prem"
    
    this._service.updateBooking(this.booking).subscribe((response:any)=>{
      console.log(response);
    })

  }

}
