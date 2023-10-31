import { Component, OnInit } from '@angular/core';

import { ApiService } from '../bookingShared/api.service';

@Component({
  selector: 'app-booking-status',
  templateUrl: './booking-status.component.html',
  styleUrls: ['./booking-status.component.css']
})
export class BookingStatusComponent implements OnInit {
  
  
  emailId:string = sessionStorage.getItem("emailId");
  bookings:any[];
  constructor( private apiservice:ApiService) { }

  ngOnInit(): void {
//this.apiservice.getBooking("pk15hari@gmail.com").subscribe(data=>{
 // this.bookings=data;
//})
 this.getAllData();
 //this.getData();
  }
getAllData(){
  this.apiservice.getBooking(this.emailId).subscribe(res=>{
    this.bookings=res;
    console.log(res);
  })
}

CancelBooking(data:any,i:number){
  console.log(i);
  this.apiservice.cancelBooking(data.bookingId).subscribe(res=>{
    
    this.getAllData();//Quick refresh data
   
  })
  
}
  
// getData(){
//   this.apiservice.getbooking("bookingId").subscribe(res=>{
//     this.bookings=res;
//   })

//   }
logout(){
  sessionStorage.clear();
}
}