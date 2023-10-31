import { BookingStatusComponent } from './booking-status/booking-status.component';

import { LandingpageComponent } from './landingpage/landingpage.component';
import { HotelsearchComponent } from './hotelsearch/hotelsearch.component';
import { BussearchComponent } from './bussearch/bussearch.component';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { SupplierHeaderComponent } from './supplier-header/supplier-header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ServiceInfoComponent } from './service-info/service-info.component';
import { FlightsearchComponent } from './flightsearch/flightsearch.component';
import { TrainsearchComponent } from './trainsearch/trainsearch.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { PaymentComponent } from './payment/payment.component';
import { SupplierRegistrationComponent } from './supplier-registration/supplier-registration.component'
import { ProfileUserComponent } from './profile-user/profile-user.component';
import { SearchserviceComponent } from './searchservice/searchservice.component';
import { ServicePostedComponent } from './service-posted/service-posted.component';
import { LoginComponent } from './login/login.component';
import { CustomerComponent } from './customer/customer.component';
import { SupplierComponent } from './supplier/supplier.component';
import { DataService } from './data.service';
import { ProfileSupplierComponent } from './profile-supplier/profile-supplier.component';

import { HeaderComponent } from './header/header.component';
import { AuthServiceService } from './auth-service.service';
import { UserHeaderComponent } from './user-header/user-header.component';
import { FooterComponent } from './footer/footer.component';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    UserHeaderComponent,
    SupplierHeaderComponent,
    FooterComponent,
    ServiceInfoComponent,
    FlightsearchComponent,
    TrainsearchComponent,
    BussearchComponent,
    HotelsearchComponent,
    UserRegistrationComponent,
    LandingpageComponent,
    PaymentComponent,
    SupplierRegistrationComponent,
    SearchserviceComponent,
    ServicePostedComponent,
    LoginComponent,
    CustomerComponent,
    SupplierComponent,
    ProfileUserComponent,
    SearchserviceComponent,
    ProfileSupplierComponent,
    HeaderComponent,
    BookingStatusComponent
    
    
  ],

  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [DataService,
    AuthServiceService
  ],
  schemas: [ NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA ],
  bootstrap: [AppComponent]
})
export class AppModule { }
