import { Component, OnInit } from '@angular/core';
import { ApiService } from '../shared/api.service';

@Component({
  selector: 'app-service-posted',
  templateUrl: './service-posted.component.html',
  styleUrls: ['./service-posted.component.css']
})
export class ServicePostedComponent implements OnInit {

emailId: string = sessionStorage.getItem("emailId");
busDetails:any[]=[];
trainDetails:any[]=[];
flightDetails:any[]=[];
hotelDetails:any[]=[];


  constructor(private api:ApiService) { }


  getbusDetails(){
    this.api.getBus(this.emailId).subscribe(Bus=>{
      this.busDetails=Bus;
      console.log(Bus);
    })

    
  }
  gettrainDetails(){
    this.api.getTrain(this.emailId).subscribe(tran=>{
      this.trainDetails=tran;
    })
  }

  getflightDetails(){
    this.api.getFlight(this.emailId).subscribe(res=>{
      this.flightDetails=res;
    })
  }

  gethotelDetails(){
    this.api.getHotel(this.emailId).subscribe(hotl=>{
      this.hotelDetails=hotl;
    })
  }

  ngOnInit(): void {
    this.getbusDetails();
    this.gettrainDetails();
    this.getflightDetails();
    this.gethotelDetails();
  }

}
