import { RazorpayService } from './../razorpay.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { PaymentService } from './payment.service';

declare let Razorpay: any;
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  constructor(
    private razorpayService: PaymentService,
    private cd: ChangeDetectorRef,
    private razorpay: RazorpayService
  ) {}
 
  name = 'Angular';
  response;
  razorpayResponse;
  showModal = false;

  ngOnInit() {
    this.razorpayService
      .lazyLoadLibrary('https://checkout.razorpay.com/v1/checkout.js')
      .subscribe();
  }

  RAZORPAY_OPTIONS = {
    key: 'rzp_test_WQNKvWQvERoXK4',
    amount: '',
    name: 'Razorpay',
    order_id: '',
    description: 'Load Wallet',
    prefill: {
      name: 'Rohith',
      email: 'rohith@gmail.com',
      contact: '9876543201',
    },
    modal: {},
    theme: {
      color: '#0096C5',
    },
  };

  public proceed(amount: any) {
    let order={
      "paymentId" : "",
      "orderId" : "",
      "userEmailId" : "rohith@123",
      "totalPrice" : amount,
      "status" : "passed",
      "razorpayOrderId" : " "
     }
    this.razorpay.createOrder(order).subscribe(resp=>{
      console.log(resp.razorpayOrderId);
      this.RAZORPAY_OPTIONS.order_id=resp.razorpayOrderId;
      this.RAZORPAY_OPTIONS.amount = 100 + '00';

    // binding this object to both success and dismiss handler
    this.RAZORPAY_OPTIONS['handler'] = this.razorPaySuccessHandler.bind(this);

    // this.showPopup();

    let razorpay = new Razorpay(this.RAZORPAY_OPTIONS);
    razorpay.open();
    })
    
  }

  public razorPaySuccessHandler(response) {
    console.log(response);
    this.razorpayResponse = `Payment Successful`;
    this.showModal = true;
    this.cd.detectChanges();
    document.getElementById('razorpay-response').style.display = 'block';
  }
}
