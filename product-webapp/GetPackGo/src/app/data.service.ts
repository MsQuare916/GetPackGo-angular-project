import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking, IFlight, Provider, User } from './model';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class DataService {

  private _flighturl : string = `${environment.url}/supplier/api/v1/flights`;
  private _usersUrl: string = `${environment.url}/user/api/v1/users`;
  private _providerUrl: string = `${environment.url}/user/api/v1/providers`
  private _bookingUrl : string = `${environment.url}/booking/api/v1/Booking`;
  public flights = [];
  setflightform(data:any){
    this.flights=data;
  }
  getflightform(){
    return this.flights;
  }

  constructor(private http:HttpClient) { }

  //methods for Flights,Hotels,Bus,Train Services 
  getAllFlights(): Observable<IFlight[]>{
    return this.http.get<IFlight[]>(this._flighturl);
  }
  getSearchFlights(fdepname:any,farrname:any,fdate:any){
    return this.http.get<any>(`${environment.url}/supplier/api/v1/flight/`+fdepname+"/"+farrname+"/"+fdate)
    .pipe((map((res:any)=>{return res;})))
  }

  getSearchHotels(hname:any){
    return this.http.get<any>(`${environment.url}/supplier/api/v1/hotelname/`+hname)
    .pipe((map((res:any)=>{return res;})))
  }

  getSearchTrains(tdepname,tarrname,tdate) {
    return this.http.get<any>(`${environment.url}/supplier/api/v1/train/`+tdepname+"/"+tarrname+"/"+tdate)
    .pipe((map((res:any)=>{return res;})))
  }

  getSearchBuses(bdepname,barrname,bdate){
    return this.http.get<any>(`${environment.url}/supplier/api/v1/bus/`+bdepname+"/"+barrname+"/"+bdate)
    .pipe((map((res:any)=>{return res;})))
  }

  // methods for userServices
  getAllUsers(){
    return this.http.get<any>(this._usersUrl);
  }

  getUserByEmail(primaryMail){
    return this.http.get<any>(`${environment.url}/user/api/v1/user/` + primaryMail)
  }

  saveOrUpdateUser(user: User){
    return this.http.put<any>(`${environment.url}/user/api/v1/user/save`, user);
  }


  // mehtods for providerservice
  getAllProviders(){
    return this.http.get<any>(this._providerUrl);
  }
  
  getProviderByEmail(primaryMail){
    return this.http.get<any>(`${environment.url}/user/api/v1/provider/` + primaryMail);
  }

  saveOrUpdateProvider(provider: Provider){
    return this.http.put<any>(`${environment.url}/user/api/v1/provider/save`, provider);
  }

  //method for booking service
  updateBooking(booking : Booking) {
    return this.http.post<any>(this._bookingUrl, JSON.stringify(booking), {'headers':{'Content-Type':'application/json'}});
  }
}