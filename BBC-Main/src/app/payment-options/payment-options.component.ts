import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment-options',
  templateUrl: './payment-options.component.html',
  styleUrls: ['./payment-options.component.css']
})
export class PaymentOptionsComponent implements OnInit {
  customerId: any = sessionStorage.getItem('customerId');
  billId: any = sessionStorage.getItem('billId');
  billAmount = sessionStorage.getItem('billAmount');
  paid:any = sessionStorage.getItem('paid');
  singlebill=[];

  cardNo!: string;
  expirate!: Date;
  cv!: string;

  debitCardNo!: string;
  debitCvv!: string;

  upiId!: string;

  walletName!: string;

  selectedPaymentMethod: string | null = null;

  creditCardMessage: string | null = null;
  debitCardMessage: string | null = null;
  walletMessage: string | null = null;
  upiMessage: string | null = null;

  creditCardDetailsEntered = false;
  debitCardDetailsEntered = false;
  walletDetailsEntered = false;
  upiDetailsEntered = false;
  errorMessage = "";

  constructor(private httpService: HttpService,private router: Router) {}

  ngOnInit(): void {}

  selectPaymentMethod(paymentMethod: string) {
    this.selectedPaymentMethod = paymentMethod;
  }

  checkCreditCard(cardNo: any, expire: any, cv: any) {
    const creditCard = {
      cardNumber: cardNo,
      expiration: expire,
      cvv: cv
    };

    console.log(creditCard);

    this.httpService.checkCreditCardExistence(this.customerId, creditCard).subscribe((isValid: boolean) => {
      if (isValid) {
        this.creditCardDetailsEntered = true;
        this.creditCardMessage = 'Credit card is valid.';
      } else {
        this.creditCardMessage = 'Credit card is not valid.';
      }
    });
  }

  checkDebitCard(debitCardNo: any, debitCvv: any) {
    const creditCard = {
      cardNumber: debitCardNo,
      cvv: debitCvv
    };


    this.httpService.checkDebitCardExistence(this.customerId, creditCard).subscribe((isValid: boolean) => {
      if (isValid) {
        this.debitCardMessage = 'Credit card is valid.';
        this.debitCardDetailsEntered = true;
      } else {
        this.debitCardMessage = 'Credit card is not valid.';
      }
    });
  }

  checkUpi(upiId: any) {
    const upi = {
      upiId: upiId
    };

    this.httpService.checkUpiValidity(this.customerId, upi).subscribe((isValid: boolean) => {
      if (isValid) {
        this.upiDetailsEntered = true;
        this.upiMessage = 'UPI is valid.';
      } else {
        this.upiMessage = 'UPI is not valid.';
      }
    });
  }

  checkWallet(walletName: any) {
    const wallet = {
      walletName: walletName
    };

    this.httpService.checkWalletExistence(this.customerId, wallet).subscribe((isValid: boolean) => {
      if (isValid) {
        this.walletDetailsEntered = true;
        this.walletMessage = 'Wallet is valid.';
      } else {
        this.walletMessage = 'Wallet is not valid.';
      }
    });
  }

  payBillByCreditCard() {
    this.httpService.payBillByCreditCard(this.customerId, this.billId).subscribe((response: string) => { 
      if(response == '1'){
        this.handlePaymentSuccess();}
        else{
          this.errorMessage="InSufficient Balance";
        }
    }, (error: any) => {
      this.handlePaymentError();
    }
    );
  }
  payBillByDebitCard() {
    this.httpService.payBillByDebitCard(this.customerId, this.billId).subscribe((response: string) => {
      if(response == '1'){
      this.handlePaymentSuccess();}
      else{
        this.errorMessage="InSufficient Balance";
      }
    },
    (error: any) => {
      this.handlePaymentError();
    }
    );
  }
  payBillByUpi() {
    this.httpService.payBillByUpi(this.customerId, this.billId).subscribe((response: string) => {
      if(response == '1'){
        this.handlePaymentSuccess();}
        else{
          this.errorMessage="InSufficient Balance";
        }
      },
      (error: any) => {
        this.handlePaymentError();
      });
  }
  
  payBillByWallet() {
    this.httpService.payBillByWallet(this.customerId, this.billId).subscribe((response: string) => {
      if(response == '1'){
        this.handlePaymentSuccess();}
        else{
          this.errorMessage="InSufficient Balance";
        }
    },
    (error: any) => {
      this.handlePaymentError();
    });
  }
  
  handlePaymentSuccess() {
    if (this.paid) {
      this.router.navigate(['/success']);
    } else {
    }
  }
  handlePaymentError() {
  }

  }

