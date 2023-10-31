import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthServiceService } from '../auth-service.service';

@Component({
  selector: 'app-supplier-registration',
  templateUrl: './supplier-registration.component.html',
  styleUrls: ['./supplier-registration.component.css']
})
export class SupplierRegistrationComponent implements OnInit {

  title: string ="Register as Supplier";
  myform: FormGroup;
  
  constructor(private http: HttpClient, private loginService: AuthServiceService, private router: Router) {
    this.myform = new FormGroup({
      firstName: new FormControl("", [Validators.required, this.containsSpace]),
      middleName: new FormControl("", [this.containsSpace]),
      lastName: new FormControl("", [Validators.required, this.containsSpace]),
      primaryMail: new FormControl("", [Validators.required, Validators.email]),
      //secondaryMail: new FormControl("", []),
      mobileNo1: new FormControl("", [Validators.required, Validators.pattern("^((\\+91-?)|0)[0-9]{10}$")]),
      //mobileNo2: new FormControl("", [Validators.pattern("^((\\+91-?)|0)[0-9]{10}$")]),
      //preferedLanguage: new FormControl(null, []),
      //userName: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required, Validators.pattern("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}")]),
      confirmPassword: new FormControl("", [Validators.required, Validators.pattern("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}"), /*this.isPasswordEqual*/]),
      //photo: new FormControl(null, [Validators.required])
      })
   }

  ngOnInit(): void {
  }

  public containsSpace(control: FormControl){
    if ((control.value) != null || control.value != ""){
      if((control.value as string).indexOf(' ') >= 0){
        return {containsSpace: true}
      }
    }
    return {containsSpace: false}
  }

  reg():void{
    if (this.myform.value.password != this.myform.value.confirmPassword){
      alert("Your passwords are not matching please enter it again!");
      this.myform.controls['password'].reset();
      this.myform.controls['confirmPassword'].reset();
      return null;
    }
    else if(this.validInputs()){
      alert("Incorrect values!");
      return null;
    }
    else{
      console.log(this.myform.value);
      this.http.post(`${`${environment.url}/user/api/v1/provider`}`, this.myform.value).subscribe(data=>{
          alert("user is registred")
        },error=>{
          var user = {
            'email': this.myform.value.primaryMail,
            'password': this.myform.value.password,
            'role': "SUPPLIER"
          }
          console.log(user);
          this.loginService.updateUserModel(user).subscribe(data=> {
            this.router.navigate(['/login']);
            alert("Please Login now!");
          }); 
        }
        // alert("error")
      );
    }
  }
  
  validInputs():Boolean{
    if (this.myform.value.firstName == "" ||
      this.myform.value.lastName == "" ||
      this.myform.value.email == "" ||
      this.myform.value.password == "" ||
      this.myform.value.confirmPassword == ""){
        return true;
    }
    else if(this.myform.controls.firstName.errors?.containsSpace 
      || this.myform.controls.lastName.errors?.containsSpace 
      || this.myform.controls.middleName.errors?.containsSpace
      || this.myform.controls.primaryMail.errors?.email
      || this.myform.controls.mobileNo1.errors?.pattern
      || this.myform.controls.password.errors?.pattern
      || this.myform.controls.firstName.touched && this.myform.controls.firstName.invalid && this.myform.controls.firstName.errors.required
      || this.myform.controls.lastName.touched && this.myform.controls.lastName.invalid && this.myform.controls.lastName.errors.required
      || this.myform.controls.primaryMail.touched && this.myform.controls.primaryMail.invalid && this.myform.controls.primaryMail.errors.required
      || this.myform.controls.mobileNo1.touched && this.myform.controls.mobileNo1.invalid && this.myform.controls.mobileNo1.errors.required
      || this.myform.controls.password.touched && this.myform.controls.password.invalid && this.myform.controls.password.errors.required
      || this.myform.controls.confirmPassword.touched && this.myform.controls.confirmPassword.invalid && this.myform.controls.confirmPassword.errors.required){
        return true;
      }
    return false;
  }

  clear():void{
    this.myform.reset();
  }
}
