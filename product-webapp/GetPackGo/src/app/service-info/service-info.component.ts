import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { Bus } from '../model';
import { ApiService } from '../shared/api.service';
import { BusData, FlightData, HotelData, TrainData } from './service-info.model';

@Component({
  selector: 'app-service-info', 
  templateUrl: './service-info.component.html',
  styleUrls: ['./service-info.component.css']
})
export class ServiceInfoComponent implements OnInit {

  emailId: string = sessionStorage.getItem("emailId");
  alert:boolean = false;

  myform1:FormGroup;
  myform2:FormGroup;
  myform3:FormGroup;
  myform4:FormGroup;
 
 flightModelObj: FlightData = new FlightData;
 hotelModelObj: HotelData = new HotelData;
 trainModelObj: TrainData = new TrainData;
 busModelObj: BusData = new BusData;
 /*busDetails:any[]=[];*/

  constructor(private api:ApiService) { 
    this.myform1=new FormGroup({
      emailId :new FormControl("",[Validators.required]),
      flightId :new FormControl("",[Validators.required]),
      flightName :new FormControl("",[Validators.required]),
      departureAirportName : new FormControl("",[Validators.required]),
      departureTime :new FormControl("",[Validators.required]),
      startTime :new FormControl("",[Validators.required]),
      endTime :new FormControl("",[Validators.required]),
      arrivalAirportName :new FormControl("",[Validators.required]),
      arrivalTime :new FormControl("",[Validators.required]),
      flightTime :new FormControl("",[Validators.required]),
      flightCost :new FormControl("",[Validators.required])
    })

    this.myform2=new FormGroup({
      emailId :new FormControl("",[Validators.required]),
      hotelId :new FormControl("",[Validators.required]),
      hotelAddress : new FormControl("",[Validators.required]),
      hotelName :new FormControl("",[Validators.required]),
      hotelOwnerName :new FormControl("",[Validators.required]),
      roomCostPerDay :new FormControl("",[Validators.required]),
      summary :new FormControl("",[Validators.required])
    })

    this.myform3=new FormGroup({
      emailId :new FormControl("",[Validators.required]),
      trainId :new FormControl("",[Validators.required]),
      trainName :new FormControl("",[Validators.required]),
      departurePlatformName : new FormControl("",[Validators.required]),
      departureTime :new FormControl("",[Validators.required]),
      //trainJourneyDetails :new FormControl("",[Validators.required]),
      arrivalPlatformName :new FormControl("",[Validators.required]),
      arrivalTime :new FormControl("",[Validators.required]),
      trainCost :new FormControl("",[Validators.required])
    })

    this.myform4=new FormGroup({
      emailId :new FormControl("",[Validators.required]),
      busId :new FormControl("",[Validators.required]),
      busName :new FormControl("",[Validators.required]),
      departureStation : new FormControl("",[Validators.required]),
      departureTime :new FormControl("",[Validators.required]),
      //busJourneyDetails :new FormControl("",[Validators.required]),
      arrivalStation :new FormControl("",[Validators.required]),
      arrivalTime :new FormControl("",[Validators.required]),
      cost :new FormControl("",[Validators.required])
    })
  
  } 
  addFlight(){
    this.flightModelObj.emailId=this.myform1.value.emailId;
    this.flightModelObj.flightId=this.myform1.value.flightId;
    this.flightModelObj.flightName=this.myform1.value.flightName;
    this.flightModelObj.departureAirportName=this.myform1.value.departureAirportName;
    this.flightModelObj.departureTime=this.myform1.value.departureTime;
    this.flightModelObj.arrivalAirportName=this.myform1.value.arrivalAirportName;
    this.flightModelObj.arrivalTime=this.myform1.value.arrivalTime;
    // this.flightModelObj.flightTime=this.myform1.value.flightTime;
    this.flightModelObj.startTime=this.myform1.value.startTime;
    this.flightModelObj.endTime=this.myform1.value.endTime;
    this.flightModelObj.flightCost=this.myform1.value.flightCost;

    this.api.postFlight(this.flightModelObj).subscribe(res=>{
      console.log(res);
      // alert("added")
      this.myform1.value.reset()
    },
    err=>{
      this.alert = true;
      setTimeout(()=>{
        this.alert = false;
      }, 2000)
      // alert("failed")
    }
    )
  }

  addHotel(){
    this.hotelModelObj.emailId=this.myform2.value.emailId;
    this.hotelModelObj.hotelId=this.myform2.value.hotelId;
    this.hotelModelObj.hotelAddress=this.myform2.value.hotelAddress;
    this.hotelModelObj.hostelName=this.myform2.value.hotelName;
    this.hotelModelObj.hostelOwnerName=this.myform2.value.hotelOwnerName;
    this.hotelModelObj.roomCostPerDay=this.myform2.value.roomCostPerDay;
    this.hotelModelObj.summary=this.myform2.value.summary;

    this.api.postHotel(this.hotelModelObj).subscribe(hotl=>{
      console.log(hotl);
      // alert("Hotel added")
      this.myform2.reset()
    },
    err=>{
      this.alert=true, 3000;
      // alert("failed")
    }
    )
  }


  addTrain(){
    this.trainModelObj.emailId=this.myform3.value.emailId;
    this.trainModelObj.trainId=this.myform3.value.trainId;
    this.trainModelObj.trainName=this.myform3.value.trainName;
    this.trainModelObj.departurePlatformName=this.myform3.value.departurePlatformName;
    this.trainModelObj.departureTime=this.myform3.value.departureTime;
    //this.trainModelObj.trainJourneyDetails=this.myform3.value.trainJourneyDetails;
    this.trainModelObj.arrivalPlatformName=this.myform3.value.arrivalPlatformName;
    this.trainModelObj.arrivalTime=this.myform3.value.arrivalTime;
    this.trainModelObj.trainCost=this.myform3.value.trainCost;

    this.api.postTrain(this.trainModelObj).subscribe(tran=>{
      console.log(tran);
      // alert("train added")
      this.myform3.reset()
    },
    err=>{
      this.alert=true, 3000;
      // alert("failed")
    }
    )
  }


  addBus(){
    this.busModelObj.emailId=this.myform4.value.emailId;
    this.busModelObj.busId=this.myform4.value.busId;
    this.busModelObj.busName=this.myform4.value.busName;
    this.busModelObj.departureStation=this.myform4.value.departureStation;
    this.busModelObj.departureTime=this.myform4.value.departureTime;
   // this.busModelObj.busJourneyDetails=this.myform4.value.busJourneyDetails;
    this.busModelObj.arrivalStation=this.myform4.value.arrivalStation;
    this.busModelObj.arrivalTime=this.myform4.value.arrivalTime;
    this.busModelObj.cost=this.myform4.value.cost;
  

    this.api.postBus(this.busModelObj).subscribe(bus=>{
      console.log(bus);
      // alert("Bus added")
      this.myform4.reset()
    },
    err=>{
      // alert("failed")
    }
    )
  }
 

  ngOnInit(): void {
    /*this.getbusDetails();*/
  }
}





