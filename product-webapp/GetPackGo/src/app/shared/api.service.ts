import { HttpClient } from '@angular/common/http';
import { ReturnStatement } from '@angular/compiler';
import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private _http:HttpClient) { }
  postFlight(data:any){
    return this._http.post<any>(`${environment.url}/supplier/api/v1/flight`,data).pipe(map((res:any)=>{
      return res;
    }))
  }

 

  postHotel(data:any){
    return this._http.post<any>(`${environment.url}/supplier/api/v1/hotel`,data).pipe(map((hotl:any)=>{
      return hotl;
    }))
  }
  
  postTrain(data:any){
    return this._http.post<any>(`${environment.url}/supplier/api/v1/train` ,data).pipe(map((tran:any)=>{
      return tran;
    }))
  }
  
  postBus(data:any){
    return this._http.post<any>(`${environment.url}/supplier/api/v1/bus`,data).pipe(map((bus:any)=>{
      return bus;
    }))
  }

  getBus(emailId:String){
    return this._http.get<any>(`${environment.url}/supplier/api/v1/buses/`+emailId)
    .pipe(map((bus:any)=>{
      return bus;
    }))
  }

  getTrain(emailId:String){
    return this._http.get<any>(`${environment.url}/supplier/api/v1/trains/`+emailId)
    .pipe(map((tran:any)=>{
      return tran;
    }))
  }

  getFlight(emailId:String){
    return this._http.get<any>(`${environment.url}/supplier/api/v1/flights/`+emailId)
    .pipe(map((res:any)=>{
      return res;
    }))
  }

  getHotel(emailId:String){
    return this._http.get<any>(`${environment.url}/supplier/api/v1/hotels/`+emailId)
    .pipe(map((hotl:any)=>{
      return hotl;
    }))
  }
}
