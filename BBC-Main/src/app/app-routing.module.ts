import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { BillComponent } from './bill/bill.component';
import { PaymentsComponent } from './payments/payments.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { HomeComponent } from './home/home.component';
import { PaymentOptionsComponent } from './payment-options/payment-options.component';
import { TransactionComponent } from './transaction/transaction.component';

const routes: Routes = [
  {
    path : "",component:LoginComponent
  },
  {
    path : "bill",component:BillComponent
  }
  ,
  {
    path : "payment",component:PaymentsComponent
  }
,
{
  path:"home",component:HomeComponent
}
,
{
  path:"payment-methods",component:PaymentOptionsComponent
},
{
  path:"success",component:TransactionComponent
},
{
  path:"**",component:NotfoundComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
