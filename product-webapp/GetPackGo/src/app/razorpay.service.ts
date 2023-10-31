import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RazorpayService {

  constructor(private httpclient: HttpClient) { }

  createOrder(order: any){
    
    return this.httpclient.post<any>(`${environment.url}/payment/api/v1/payNow`, order)
  }
}
