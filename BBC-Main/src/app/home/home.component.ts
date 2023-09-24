import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  customerId!: any;
  customerName: string = ''; 

  constructor(private service: HttpService) { }

  ngOnInit(): void {
    this.customerId = sessionStorage.getItem('customerId');
    this.fetchCustomerName();
  }

  fetchCustomerName() {
    this.service.getCustomerName(this.customerId).subscribe(
      (name: string) => {
        this.customerName = name;
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }
}
