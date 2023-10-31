import { Router, RouterModule } from '@angular/router';
import { Bus, Hotel, Train } from './../model';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { IFlight } from '../model';

@Component({
  selector: 'app-searchservice',
  templateUrl: './searchservice.component.html',
  styleUrls: ['./searchservice.component.css']
})
export class SearchserviceComponent implements OnInit {

  flightform:FormGroup;
  fdepname:string;
  farrname:string;
  fdate: any;

  hotelform:FormGroup;
  hname:string;

  trainform:FormGroup;
  tdepname:string;
  tarrname:string;
  tdate :any;

  busform:FormGroup;
  bdepname:string;
  barrname:string;
  bdate :any;
  busObj : Bus =new Bus;
 
  constructor(private _service : DataService, private router : Router) { 

    this.flightform=new FormGroup({
      fdepname:new FormControl("",[Validators.required]),
      farrname:new FormControl("",[Validators.required]),
      fdate: new FormControl("",[Validators.required])
    })
    
    this.hotelform=new FormGroup({
      hname:new FormControl("",[Validators.required])
    })

    this.trainform=new FormGroup({
      tdepname:new FormControl("",[Validators.required]),
      tarrname:new FormControl("",[Validators.required]),
      tdate: new FormControl("",[Validators.required])
    })

    this.busform=new FormGroup({
      bdepname:new FormControl("",[Validators.required]),
      barrname:new FormControl("",[Validators.required]),
      bdate: new FormControl("",[Validators.required])
    })

  }

  flightObj : IFlight =new IFlight;
  public searchFlights = [];

  onSubmitFlight(form:any){
    this.flightObj.departureAirportName= form.value.fdepname;
    this.flightObj.arrivalAirportName=form.value.farrname;
    this.flightObj.departureTime=form.value.fdate;
    console.log(form.value);
    
    this._service.getSearchFlights(this.flightObj.departureAirportName,this.flightObj.arrivalAirportName,this.flightObj.departureTime)
    .subscribe(data => this.searchFlights = data)

    this._service.setflightform(this.searchFlights);
    console.log(this.searchFlights);
    
    form.reset();

    this.router.navigate(["search/flight",this.flightObj.departureAirportName,this.flightObj.arrivalAirportName,this.flightObj.departureTime])

  }


  hotelObj : Hotel = new Hotel;
  public searchHotels = [];

  onSubmitHotel(form:any){
    this.hotelObj.hotelName =form.value.hname;
    console.log(form.value);
    this._service.getSearchHotels(this.hotelObj.hotelName)
    .subscribe(data => this.searchHotels = data);
    form.reset();

    this.router.navigate(["search/hotel",this.hotelObj.hotelName])

  }

  
  trainObj : Train = new Train;
   public searchTrains = [];

   onSubmitTrain(form:any){
    this.trainObj.departurePlatformName = form.value.tdepname;
    this.trainObj.arrivalPlatformName = form.value.tarrname;
    this.trainObj.departureTime= form.value.tdate;
    console.log(form.value);
    this._service.getSearchTrains(this.trainObj.departurePlatformName,this.trainObj.arrivalPlatformName,this.trainObj.departureTime)
    .subscribe(data => this.searchTrains = data);
    form.reset();

    this.router.navigate(["search/train",this.trainObj.departurePlatformName,this.trainObj.arrivalPlatformName,this.trainObj.departureTime])

  }

 
  public searchBuses = [];

   onSubmitBus(form:any){
    this.busObj.departureStation = form.value.bdepname;
    this.busObj.arrivalStation = form.value.barrname;
    this.busObj.departureTime = form.value.bdate;
    this._service.getSearchBuses(this.busObj.departureStation,this.busObj.arrivalStation,this.trainObj.departureTime)
    .subscribe(data => this.searchBuses = data);
    form.reset();
    console.log(form.value);

    this.router.navigate(["search/bus",this.busObj.departureStation,this.busObj.arrivalStation,this.busObj.departureTime])
  }
  
  ngOnInit(): void {
  }

}
