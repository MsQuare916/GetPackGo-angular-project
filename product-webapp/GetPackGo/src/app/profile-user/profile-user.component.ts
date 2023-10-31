import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { User } from '../model';

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit {
  
  userForm: FormGroup = null;
  userId: string = sessionStorage.getItem("emailId");
  disable: Boolean = false;
  onsubmit: any;
  user: User = new User();

  constructor(private _service: DataService) {
   
    this._service.getUserByEmail(this.userId)
    .subscribe(data => {
      this.user=data;  
    
    //form
    this.userForm = new FormGroup({
      firstName: new FormControl({value: this.user.firstName, disabled: true}, [Validators.required]),
      middleName: new FormControl({value: this.user.middleName, disabled: true}, []),
      lastName: new FormControl({value: this.user.lastName, disabled: true}, [Validators.required]),
      primaryMail: new FormControl({value: this.user.primaryMail, disabled: true}, [Validators.email]),
      secondaryMail: new FormControl({value: this.user.secondaryMail, disabled: true}, [Validators.email]),
      mobileNo1: new FormControl({value: this.user.mobileNo1, disabled: true}, []),
      mobileNo2: new FormControl({value: this.user.mobileNo2, disabled: true}, []),
      address: new FormControl({value: this.user.address, disabled: true}, []),
      preferedLanguage: new FormControl({value: this.user.preferedLanguage, disabled: true}, []),
      photo: new FormControl({value: "", disabled: true}, []),
      panCardNo: new FormControl({value: this.user.panCardNo, disabled: true}, []),
      gender: new FormControl({value: "", disabled: true}, []),
      //dateOfBirth: new FormControl({value: "", disabled: true}, [])
    })
    });

  }

  ngOnInit(): void {
    
  }

  enableForm():void{
    this.userForm.enable();
    this.disable = true;
  }
  disableForm(): void{
    this.userForm.disable();
    this.disable = false;
    this.reset();
  }

  reset():void{
    
    this.userForm.reset({
      firstName: this.user.firstName,
      middleName: this.user.middleName,
      lastName: this.user.lastName,
      primaryMail: this.user.primaryMail,
      gender: "",
      mobileNo1: this.user.mobileNo1,
      mobileNo2: this.user.mobileNo2,
      panCardNo: this.user.panCardNo,
      secondaryMail: this.user.secondaryMail,
      address: this.user.address,
      photo: ""
    });
  }

  submit():void{
    this.user = this.userForm.value;
    this.userForm.disable();
    this._service.saveOrUpdateUser(this.user).subscribe(data=> this.onsubmit=data);
    // console.log(this.userForm.value);
    // console.log(this.onsubmit);
  }
}
