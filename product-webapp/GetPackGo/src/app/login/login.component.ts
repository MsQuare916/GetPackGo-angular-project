import { FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormsModule, FormControl, Validators} from '@angular/forms';
//import { MatSnackBar} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthServiceService } from '../auth-service.service';
//import { JwtHelperService } from '@auth0/angular-jwt';
//import { error } from 'console';




declare var $:any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  alert:boolean=false;
  decodedToken:any;
  message:any;
  action:any;
  loginform : FormGroup;
  //helper= new JwtHelperService();

  constructor( private fb:FormBuilder, private authService:AuthServiceService,
              private router: Router,
               ) {
                this.loginform=new FormGroup({
                  email:new FormControl("",[Validators.email,Validators.required]),
                  password : new FormControl("", [Validators.required, Validators.minLength(8)]),
                  selectedrole : new FormControl("",[Validators.required])
                }


                )
               }

// loginform =this.fb.group({
//   email: [null,[Validators.email,Validators.required]],
//   password:[null,[Validators.required,Validators.minLength(8)]],
// })

ngonit(){
  $('[data-toggle="tooltip"]').tooltip();
}

rolesAvailable: string[] = ['User', 'Service']


login() {
  console.log(this.loginform.value.selectedrole)
  if(this.loginform.value.selectedrole=="customer"){
    console.log("user")
    const val = this.loginform.value;
    if (val.email && val.password) {
      console.log("apicallmade")
      this.authService.login(val.email, val.password)
          .subscribe(
              (result) => {
                  console.log(result);
                  //this.decodedToken= this.helper.decodeToken(result.token);
                  sessionStorage.setItem("emailId",this.loginform.value.email);
                  sessionStorage.setItem('token',result.token);
                  this.loginform.reset();
                  this.router.navigate(['/customer']);
                  // .then(()=>{
                  //   this.snackbar.open("You are logged in!","OK",{
                  //     duration:2500,
                  //   });
                  // });
              },

              error=>{
                console.log("incorrect details") ;
                this.alert=true;
                setTimeout(() => {
                  this.alert=false;
                }, 3000);
              }
          )
    
          
                  
  }
    
  }
  else if(this.loginform.value.selectedrole=="service"){
    const val = this.loginform.value;
    if (val.email && val.password) {
      this.authService.login(val.email, val.password)
          .subscribe(
              (result) => {
                  console.log(result);
                  //this.decodedToken= this.helper.decodeToken(result.token);
                  sessionStorage.setItem("emailId",this.loginform.value.email);
                  sessionStorage.setItem('token',result.token);
                  this.loginform.reset();
                  this.router.navigate(['/supplier']);
                  // .then(()=>{
                  //   this.snackbar.open("You are logged in!","OK",{
                  //     duration:2500,
                  //   });
                  // });
              },

              error=>{
                console.log("incorrect details") ;
                this.alert=true;
                setTimeout(() => {
                  this.alert=false;
                }, 3000);
              }
          )
    
          
                  
  }
    
    
  else{
    console.log("enter correct details")
    alert("enter correct details");
    this.loginform.reset();
  }
}
}


  }


