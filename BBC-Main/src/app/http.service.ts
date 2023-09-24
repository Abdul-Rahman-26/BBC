import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  checkWallet(customerId: number, wallet: { name: any; }) {
    throw new Error('Method not implemented.');
  }
  private baseUrl = 'http://localhost:8080/bbc/customer';

  private customerIdSubject = new BehaviorSubject<number>(0);

  constructor(private http: HttpClient) {}

  sendCustomerId(customerId:number){
    this.customerIdSubject.next(customerId);
  }

  getCustomerId(){
    return this.customerIdSubject.asObservable();
  }

  login(customerId: number) {
    const url = `${this.baseUrl}/login`;
    const params = { customerId: customerId.toString() };

    return this.http.post<boolean>(url, null, { params });
  }

  sendMail(customerId: number) {
    const url = `${this.baseUrl}/email/send/${customerId}`;
    return this.http.get(url);
  }

  checkOtp(customerId: number, otp: string) {
    const url = `${this.baseUrl}/email/${customerId}/otp`; 
    
    const params = { otp: otp.toString() }
    return this.http.post<boolean>(url, null,{params});
  }
  getBillsByCustomerId(customerId: any): Observable<any> {
    return this.http.get<any[]>(`${this.baseUrl}/${customerId}/bills`);
  }

  getPaidBillsByCustomerId(customerId: number): Observable<any[]> {
    const url = `${this.baseUrl}/${customerId}/bills/paid`;
    return this.http.get<any[]>(url);
  }
  
  getNotPaidBillsByCustomerId(customerId: number): Observable<any[]> {
    const url = `${this.baseUrl}/${customerId}/bills/not-paid`;
    return this.http.get<any[]>(url);
  }
  
  checkCreditCardExistence(customerId: number, creditCard: any): Observable<boolean> {
    const url = `${this.baseUrl}/bills/${customerId}/check-credit-card`;
    return this.http.post<boolean>(url, creditCard);
  }
  
  checkDebitCardExistence(customerId: number, debitCard: any): Observable<boolean> {
    const url = `${this.baseUrl}/bills/${customerId}/check-debit-card`;
    return this.http.post<boolean>(url, debitCard);
  }

checkUpiValidity(customerId: number, upi: any): Observable<boolean> {
  const url = `${this.baseUrl}/bills/${customerId}/check-upi`;
  return this.http.post<boolean>(url, upi);
}
checkWalletExistence(customerId: number, wallet: any): Observable<boolean> {
  const url = `${this.baseUrl}/bills/${customerId}/check-wallet`;
  return this.http.post<boolean>(url, wallet);
}
payBillByCreditCard(customerId: number, billId: number) {
  const url = `${this.baseUrl}/bills/${customerId}/${billId}/credit-card-pay`;
  return this.http.put(url, null, { responseType: 'text' });
}
payBillByDebitCard(customerId: number, billId: number) {
  const url = `${this.baseUrl}/bills/${customerId}/${billId}/debit-card-pay`;
  return this.http.put(url, null,{responseType:'text'});
}
payBillByUpi(customerId: number, billId: number) {
  const url = `${this.baseUrl}/bills/${customerId}/${billId}/upi-pay`;
  return this.http.put(url, null,{responseType:'text'});
}
payBillByWallet(customerId: number, billId: number) {
  const url = `${this.baseUrl}/bills/${customerId}/${billId}/wallet-pay`;
  return this.http.put(url, null,{responseType:'text'});
}
getBillByCustomerIdAndBillId(customerId: number, billId: number): Observable<any> {
  const url = `${this.baseUrl}/${customerId}/${billId}/get-bill`;
  return this.http.get(url);
}
getAllPaymentsByCustomerId(customerId: number): Observable<any> {
  const url = `${this.baseUrl}/bills/${customerId}/payment`; 
  return this.http.get(url);
}
getCustomerName(customerId: number) {
  const url = `${this.baseUrl}/${customerId}/getname`;
  return this.http.get(url,{
    responseType:'text'});}}
