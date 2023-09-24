import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { BillComponent } from './bill/bill.component';
import { PaymentsComponent } from './payments/payments.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { HomeComponent } from './home/home.component';
import { PaymentOptionsComponent } from './payment-options/payment-options.component';
import { TransactionComponent } from './transaction/transaction.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, HeaderComponent, BillComponent, PaymentsComponent, NotfoundComponent, HomeComponent, PaymentOptionsComponent, TransactionComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule,AppRoutingModule], 
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
