import { ActivatedRoute } from '@angular/router';
import { DataService } from './../data.service';
import { Hotel, Booking, Trains, Flights, Buses, Hotels, Cabs, ServiceType } from './../model';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-hotelsearch',
  templateUrl: './hotelsearch.component.html',
  styleUrls: ['./hotelsearch.component.css']
})
export class HotelsearchComponent implements OnInit {

  hotelform:FormGroup;
  hname:string;
  
  hotelObj : Hotel = new Hotel;

  public searchHotels = [];

  constructor(private _service : DataService , private activatedRouter : ActivatedRoute) {
    
   }



  ngOnInit(): void {
    this._service.getSearchHotels(this.activatedRouter.snapshot.paramMap.get('hname') as string)
    .subscribe(data =>{
      this.searchHotels=data
    })
  }

  public booking : Booking={
    bookingId: '',
    userEmailId: '',
    providerEmailId: '',
    serviceType: ServiceType.Hotel,
    flights: new Flights,
    trains: new Trains,
    bus: new Buses,
    hotels: new Hotels,
    cabs: new Cabs,
    booking_cancelled: false,
    checked_in: false,
    age: 0,
    name: ''
  }
public hotels : Hotels={
  hotelId: 0,
  hotel_name: '',
  location: '',
  Check_In: undefined,
  roomNo: 0,
  cost: 0
}

  bookNow(data:any){
   
    this.hotels.hotelId = data.hotelId;
    this.hotels.hotel_name = data.hotelName;
    this.hotels.location = data.hotelAddress;
    this.hotels.cost = data.roomCostPerDay;

    console.log("working for hotels");
    this.booking.userEmailId="user123@gmail.com";
    this.booking.providerEmailId="serviceprovider45@gmail.com";
    this.booking.serviceType=ServiceType.Hotel;
    this.booking.hotels=this.hotels;
    this.booking.age=22;
    this.booking.name="User Prem"
    
    this._service.updateBooking(this.booking).subscribe((response:any)=>{
      console.log(response);
    })

  }

}
