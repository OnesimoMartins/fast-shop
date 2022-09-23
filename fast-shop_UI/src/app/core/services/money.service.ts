import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MoneyService {


  private formatter=Intl.NumberFormat('en-us',{
    style:"currency",
    currency:"AOA",
    // signDisplay:"never"
    currencyDisplay:"symbol"
    })

  formatMoney(money:Number):String{
    return this.formatter.format(money as number)
  }

}
