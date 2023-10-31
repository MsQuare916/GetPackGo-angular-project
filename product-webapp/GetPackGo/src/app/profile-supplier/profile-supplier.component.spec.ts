import { ComponentFixture, TestBed } from '@angular/core/testing';

<<<<<<<< HEAD:product-webapp/GetPackGo/src/app/payment/payment.component.spec.ts
import { PaymentComponent } from './payment.component';

describe('PaymentComponent', () => {
  let component: PaymentComponent;
  let fixture: ComponentFixture<PaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentComponent ]
========
import { ProfileSupplierComponent } from './profile-supplier.component';

describe('ProfileSupplierComponent', () => {
  let component: ProfileSupplierComponent;
  let fixture: ComponentFixture<ProfileSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileSupplierComponent ]
>>>>>>>> 8caed413ac7273e7eb23df3d81d46f79c913859c:product-webapp/GetPackGo/src/app/profile-supplier/profile-supplier.component.spec.ts
    })
    .compileComponents();
  });

  beforeEach(() => {
<<<<<<<< HEAD:product-webapp/GetPackGo/src/app/payment/payment.component.spec.ts
    fixture = TestBed.createComponent(PaymentComponent);
========
    fixture = TestBed.createComponent(ProfileSupplierComponent);
>>>>>>>> 8caed413ac7273e7eb23df3d81d46f79c913859c:product-webapp/GetPackGo/src/app/profile-supplier/profile-supplier.component.spec.ts
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
