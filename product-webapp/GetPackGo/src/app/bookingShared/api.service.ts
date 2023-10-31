
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private _http:HttpClient) { }

  //Now here I will define the post,Get,put
// Create Booking using Post Method
postBooking(data:any){
  return this._http.post<any>(`${environment.url}/booking/api/v1/Booking`,data)
  .pipe(map((res:any)=>{
    return res;
  }))
}

//get booking using get method 

getBooking(emailId:string){
  return this._http.get<any>(`${environment.url}/booking/api/v1/AllBookings/`+emailId)
  .pipe(map((res:any)=>{
    return res;
  }))
}

//Cancel booking using delete method

cancelBooking(id:String){
  return this._http.delete<any>(`${environment.url}/booking/api/v1/CancelBooking/`+id)
  .pipe(map((res:any)=>{
    return res;
}))
}



getbooking(bookingId:string){
  return this._http.get<any>(`${environment.url}/booking/api/v1/bookingDetails/`+bookingId)
  .pipe(map((res:any)=>{
    return res;
  }))
}
}

