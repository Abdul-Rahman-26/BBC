import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Route, Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  customerId!: any;
  loginSuccessful = false;
  showOTPForm = false;
  otpChecker = false;

  loginMessage: string = '';
  
  OtpMessage: string = '';

  expandedAbbreviation: string = 'BBC';

  expandAbbreviation() {
    this.expandedAbbreviation = 'Bharat Bijlee Corporation';
  }

  resetAbbreviation() {
    this.expandedAbbreviation = 'BBC';
  }
  

  constructor(private service: HttpService,private router:Router) { }

  ngOnInit(): void {

  }


  onLogin(cId: any) {
    this.customerId = cId as number;
    this.service.login(this.customerId).subscribe((response) => {
      this.loginSuccessful = response;

      if (this.loginSuccessful) {
        this.showOTPForm = true;

        this.service.sendMail(this.customerId).subscribe((mailResponse) => {
          
        },
        (error) => {
          console.error('Mail Request Error:', error); 
          
        });
      }else{
        this.loginMessage = 'Login failed. Please check your Customer ID.';
      }
    });
    sessionStorage.setItem("customerId",this.customerId)
  }

  onOtp(otpValue: any) {
    this.service.checkOtp(this.customerId, otpValue).subscribe((response) => {
      this.otpChecker = response;
      if(response===true){
        this.router.navigate(['/home']);
      }else{
        this.OtpMessage='Check your mail'
      }
      console.log(response);
      
    });
  }

  goBack() {
    this.showOTPForm = false;
    this.loginSuccessful = false;
    this.loginMessage = '';
  }
  sendCustomerIdtoBill(){
    this.service.sendCustomerId(this.customerId);
  }
}
