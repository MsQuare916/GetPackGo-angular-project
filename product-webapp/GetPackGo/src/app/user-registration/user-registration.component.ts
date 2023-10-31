import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { AuthServiceService } from '../auth-service.service';
// import { threadId } from 'worker_threads';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  title = "Register";
  myform:FormGroup;
  value1: string = "English";
  value2: string = "Hindi";

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

  public containsSpace(control: FormControl){
    if ((control.value) != null || control.value != ""){
      if((control.value as string).indexOf(' ') >= 0){
        return {containsSpace: true}
      }
    }
    return {containsSpace: false}
  }

  /*public isPasswordEqual(control: FormControl){
    if (control.value != null){
      let pwd =this.myform.value.password;
      if(control.value != pwd){
        return {isPasswordEqual: false}
      }
    }
    return {isPasswordEqual: true}
  }*/

  ngOnInit(): void {
  }

  reg():void{
    // if (!this.myform.valid){
    //   alert("Enter correct details!");
    // }
    if (this.myform.value.password != this.myform.value.confirmPassword){
      alert("Your passwords are not matching please enter it again!");
      this.myform.controls['password'].reset();
      this.myform.controls['confirmPassword'].reset();
      return null;
    }
    else if(!this.validInputs()){
      alert("Incorrect values!");
      return null;
    }
    else{
      console.log(this.myform.value);
      this.http.post(`${`${environment.url}/user/api/v1/user`}`, this.myform.value).subscribe(data=>{
        
        alert("user is registred")
        },error=>{
          var user = {
            'email': this.myform.value.primaryMail,
            'password': this.myform.value.password,
            'role': "CUSTOMER"
          }
          console.log(user);
          this.loginService.updateUserModel(user).subscribe(data=> {
          this.router.navigate(['/login']);
          alert("Please Login now!");
        }); 
        }
        // alert("error"),
        // ()=>
        );
    }
  }

  validInputs():Boolean{
    if (this.myform.value.firstName == "" ||
      this.myform.value.lastName == "" ||
      this.myform.value.email == "" ||
      this.myform.value.password == "" ||
      this.myform.value.confirmPassword == ""){
        return false;
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
        return false;
      }
      else{
      return true;
      }
  }

  clear():void{
    this.myform.reset(this.myform);
  }
}



