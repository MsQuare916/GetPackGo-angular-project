import { ProfileSupplierComponent } from './profile-supplier/profile-supplier.component';
import { SearchserviceComponent } from './searchservice/searchservice.component';
import { SupplierRegistrationComponent } from './supplier-registration/supplier-registration.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceInfoComponent } from './service-info/service-info.component';
import { FlightsearchComponent } from './flightsearch/flightsearch.component';
import { HotelsearchComponent } from './hotelsearch/hotelsearch.component';
import { TrainsearchComponent } from './trainsearch/trainsearch.component';
import { BussearchComponent } from './bussearch/bussearch.component';
import { LandingpageComponent } from './landingpage/landingpage.component';
import { UserHeaderComponent } from './user-header/user-header.component';
import { SupplierHeaderComponent } from './supplier-header/supplier-header.component';
import { ServicePostedComponent } from './service-posted/service-posted.component';
import { CustomerComponent } from './customer/customer.component';
import { SupplierComponent } from './supplier/supplier.component';
import { LoginComponent } from './login/login.component';
import { PaymentComponent } from './payment/payment.component';
import { ProfileUserComponent } from './profile-user/profile-user.component';
import { BookingStatusComponent } from './booking-status/booking-status.component';




const routes: Routes = [
  {path: '', component: LandingpageComponent},
  // {path: 'user', component: UserHeaderComponent},
  // {path: 'supplier', component: SupplierHeaderComponent},
  {path: 'user/register', component: UserRegistrationComponent},
  {path: 'supplier/register', component: SupplierRegistrationComponent},
  {path : 'serviceinfo', component: ServiceInfoComponent},
  {path: 'user/profile', component: ProfileUserComponent},
  {path: 'supplier/profile', component: ProfileSupplierComponent},
  {path:'search',component:SearchserviceComponent},
  {path :'bookingstatus',component:BookingStatusComponent},
  {path: 'serviceposted', component:ServicePostedComponent},
  {path : 'search/flight/:fdepname/:farrname/:fdate', component : FlightsearchComponent},
  {path : 'search/hotel/:hname', component : HotelsearchComponent},
  {path : 'search/train/:tdepname/:tarrname/:tdate', component : TrainsearchComponent},
  {path : 'search/bus/:bdepname/:barrname/:bdate', component : BussearchComponent},
  {path :'bookingstatus',component:BookingStatusComponent},
  {path :'login',component:LoginComponent},
  {path :'customer',component:CustomerComponent},
  // {path :'service',component:SupplierComponent},
  {path :'pay',component:PaymentComponent},
  {path :'supplier',component:SupplierComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes,  {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
