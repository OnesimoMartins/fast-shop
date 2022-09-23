import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart } from '../models/Cart';
import { SaleRequest, SaleResponse } from '../models/Sale';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  url=`${environment.apiUrl}sales`

  constructor(private http:HttpClient){}

  createSale( cart:Cart,paymentMode:String):Observable<SaleResponse>{

    const sale=this.CartToSaleRequest(cart)
    sale.payment_mode=paymentMode

    console.log(sale);

    return this.http.post<SaleResponse>(this.url,sale)

  }

  comfirmSale(){}

  private CartToSaleRequest(cart:Cart):SaleRequest{
    let sale:SaleRequest={
      itens:[],
      payment_mode:''
    }

    cart.itens.forEach( (item)=>
    sale.itens.push({
      product_id:item.product.id
      ,quantity:item.quantity})

    )
    return sale
  }


}
