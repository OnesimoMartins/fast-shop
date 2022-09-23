import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/core/models/Cart';
import { CartService } from 'src/app/core/services/cart.service';
import { MoneyService } from 'src/app/core/services/money.service';
import { SaleService}from 'src/app/core/services/sale.service';
import { AppInvoiceModalService } from '../app-invoice/app-invoice-modal.service';

@Component({
  selector: 'app-app-shopping-cart',
  templateUrl: './app-shopping-cart.component.html',
  styleUrls: ['./app-shopping-cart.component.css']
})
export class AppShoppingCartComponent implements OnInit {

  navItens=[{text:"Productos" ,link:"/sale"},{text:"Carrinho" ,link:"/sale/chart"}]
  active=this.navItens[1]
  cart!:Cart

  givenMoney:Number=0
  paymentMode:string='seleccione'

  constructor(private cartService:CartService,
     private moneyService:MoneyService
     ,private saleService:SaleService,
     private modalService:AppInvoiceModalService){}

  ngOnInit() {
   this.initializeCart()
  }

  initializeCart(){
    this.cart=this.cartService.getCart()
  }

  cartHasItens():Boolean{
    return this.cart.itens.length>0
  }

  formatMoney(number:Number){
    return this.moneyService.formatMoney(number)
  }

  cleanChart(){
    this.cartService.cleanChart()
    this.initializeCart()
  }

  createSale(){
    this.saleService.createSale(this.cart,this.paymentMode).subscribe(v=>{
      this.modalService.open(v)
    })
  }

}
