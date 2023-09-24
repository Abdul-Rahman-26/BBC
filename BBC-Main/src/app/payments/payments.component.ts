import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {

  customerId: any; 
  payments: any[] = []; 

  constructor(private service: HttpService) { }

  ngOnInit(): void {
    this.customerId = sessionStorage.getItem('customerId'); 
    this.fetchPaymentsByCustomerId();
  }

  fetchPaymentsByCustomerId() {
    this.service.getAllPaymentsByCustomerId(this.customerId).subscribe((response)=>{
      this.payments =response;
    })
  }
}
