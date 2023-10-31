import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Provider } from '../model';
import { DataService } from '../data.service';

@Component({
  selector: 'app-profile-supplier',
  templateUrl: './profile-supplier.component.html',
  styleUrls: ['./profile-supplier.component.css']
})
export class ProfileSupplierComponent implements OnInit {

  providerForm: FormGroup;
  providerId: string = sessionStorage.getItem("emailId");
  disable: Boolean = false;
  onsubmit: any;
  provider: Provider;

  constructor(private _service: DataService) {

    this._service.getProviderByEmail(this.providerId)
    .subscribe(data=>{
      this.provider = data;

      //form
      this.providerForm = new FormGroup({
        firstName: new FormControl({value: this.provider.firstName, disabled: true}, []),
        middleName: new FormControl({value: this.provider.middleName, disabled: true}, []),
        lastName: new FormControl({value: this.provider.lastName, disabled: true}, []),
        primaryMail: new FormControl({value: this.provider.primaryMail, disabled: true}, []),
        secondaryMail: new FormControl({value: this.provider.secondaryMail, disabled: true}, []),
        mobileNo1: new FormControl({value: this.provider.mobileNo1, disabled: true}, []),
        mobileNo2: new FormControl({value: this.provider.mobileNo2, disabled: true}, []),
        address: new FormControl({value: this.provider.address, disabled: true}, []),
        preferedLanguage: new FormControl({value: this.provider.preferedLanguage, disabled: true}, []),
        photo: new FormControl({value: "", disabled: true}, []),
        panCardNo: new FormControl({value: this.provider.panCardNo, disabled: true}, []),
        gender: new FormControl({value: "", disabled: true}, []),
        aadharCardNo: new FormControl({value: this.provider.aadharCardNo, disabled: true}, [])
        //dateOfBirth: new FormControl({value: "", disabled: true}, [])
      })
    })
   }

  ngOnInit(): void {
  }

  enableForm():void{
    this.providerForm.enable();
    this.disable = true;
  }
  disableForm(): void{
    this.providerForm.disable();
    this.disable = false;
    this.reset();
  }

  reset():void{
    
    this.providerForm.reset({
      firstName: this.provider.firstName,
      middleName: this.provider.middleName,
      lastName: this.provider.lastName,
      primaryMail: this.provider.primaryMail,
      gender: "",
      mobileNo1: this.provider.mobileNo1,
      mobileNo2: this.provider.mobileNo2,
      panCardNo: this.provider.panCardNo,
      secondaryMail: this.provider.secondaryMail,
      address: this.provider.address,
      photo: ""
    });
  }

  submit():void{
    this.provider = this.providerForm.value;
    this.providerForm.disable();
    this._service.saveOrUpdateProvider(this.provider).subscribe(data=> this.onsubmit=data);
    // console.log(this.providerForm.value);
    // console.log(this.onsubmit);
  }

}
