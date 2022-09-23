import { Injectable } from '@angular/core';
import { Product } from '../models/Product';
import { Cart, CartItem } from '../models/Cart';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cart = new Cart();
  hasItens: boolean = false;

  addToCart(product: Product, quantity: any) {
    quantity = quantity as number;

    let itemFound = this.cart.itens.find((it) => it.product === product);


    if (quantity > 0) {
      //if item exists just update it else create
      if (itemFound) {
        itemFound.updateQuantity(quantity);
        this.cart.calculateTotal();
      } else {
        itemFound = new CartItem(product, quantity);
        this.cart.addItem(itemFound);
        this.cart.calculateTotal();
      }

      this.hasItens = this.cart.itens.length > 0;

    }else{

     this.removeCartItem(itemFound!)
     this.cart.calculateTotal()

    }

  }

  getTotalItemAmount(product: Product): Number {
    return this.cart.itens.find((e) => e.product === product)?.total as any;
  }

  private removeCartItem(cartItem: CartItem) {
    const index= this.cart.itens.indexOf(cartItem)
    if(index>-1)
     this.cart.itens.splice(index,1)
  }

  getCart(): Cart {
    return this.cart;
  }

  cleanChart(){
    this.cart=new Cart()
  }
}
