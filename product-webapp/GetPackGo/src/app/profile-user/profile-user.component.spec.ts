import { ComponentFixture, TestBed } from '@angular/core/testing';

<<<<<<<< HEAD:product-webapp/GetPackGo/src/app/profile-user/profile-user.component.spec.ts
import { ProfileUserComponent } from './profile-user.component';

describe('ProfileUserComponent', () => {
  let component: ProfileUserComponent;
  let fixture: ComponentFixture<ProfileUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileUserComponent ]
========
import { SearchserviceComponent } from './searchservice.component';

describe('SearchserviceComponent', () => {
  let component: SearchserviceComponent;
  let fixture: ComponentFixture<SearchserviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchserviceComponent ]
>>>>>>>> c3f582bc8930691bd419e2764d4d70ad6d93828a:product-webapp/GetPackGo/GetPackGo/GetPackGo/src/app/searchservice/searchservice.component.spec.ts
    })
    .compileComponents();
  });

  beforeEach(() => {
<<<<<<<< HEAD:product-webapp/GetPackGo/src/app/profile-user/profile-user.component.spec.ts
    fixture = TestBed.createComponent(ProfileUserComponent);
========
    fixture = TestBed.createComponent(SearchserviceComponent);
>>>>>>>> c3f582bc8930691bd419e2764d4d70ad6d93828a:product-webapp/GetPackGo/GetPackGo/GetPackGo/src/app/searchservice/searchservice.component.spec.ts
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
