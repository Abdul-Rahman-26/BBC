import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {

  customerId!: number;
  billId:any;
  showFilterSection = false;
  showPaidBills = false;
  showUnPaidBills = false;

  constructor(private service: HttpService, private router: Router) {}

  ngOnInit(): void {
    this.customerId = parseInt(sessionStorage.getItem("customerId") || '0', 10); 
    this.getBillsByCustomersId(this.customerId);
  }
  
  bills: any[] = []; 
  paidBills:any[]=[];
  notPaidBills:any[]=[]

  getBillsByCustomersId(customerId: number): void {
    this.service.getBillsByCustomerId(customerId).subscribe(
      (response: any) => {
        this.bills = response; 
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }

  showPaidBillsTable() {
    this.showPaidBills = true;
    this.showUnPaidBills = false;
    this.fetchPaidBillsByCustomerId();
  }

  showUnPaidBillsTable() {
    this.showPaidBills = false;
    this.showUnPaidBills = true;
    this.fetchNotPaidBillsByCustomerId();
  }

  fetchPaidBillsByCustomerId() {
    this.service.getPaidBillsByCustomerId(this.customerId).subscribe(
      (response: any[]) => {
        this.paidBills = response;
      },
      (error: any) => {
        console.error('Error fetching paid bills:', error);
      }
    );
  }

  fetchNotPaidBillsByCustomerId() {
    this.service.getNotPaidBillsByCustomerId(this.customerId).subscribe(
      (response: any[]) => {
        this.notPaidBills = response;
      },
      (error: any) => {
        console.error('Error fetching not paid bills:', error);
      }
    );
  }

  navigateToPaymentMethods(billId: number, billAmount: number,paid:boolean) {
    sessionStorage.setItem('billId', billId.toString());
    sessionStorage.setItem('billAmount', billAmount.toString()); 
    sessionStorage.setItem('paid', paid.toString())
    this.router.navigate(['/payment-methods']);
  }

  viewBill() {
    this.service.getBillByCustomerIdAndBillId(this.customerId, this.billId).subscribe(
      (response: any) => {
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }
}
