import { Product } from "./Product"

export class CartItem{
  quantity!:number
   product:Product
   total!:Number

  constructor(product:Product,initialQuantity:number){
    this.product=product
    this.updateQuantity(initialQuantity)
  }

  updateQuantity(quantity:number){

    if(this.haveEnoughQuantity(quantity))
    this.quantity=quantity

   else
     this.quantity=this.product.available_unities as any

    this.calculateTotal()
  }

  private calculateTotal(){
    this.total=(this.product.price as any)*this.quantity
  }

  private haveEnoughQuantity( qt:Number):Boolean{
    return this.product.available_unities>=qt
  }
}


export class Cart{
  totalAmount:number=0
  itens:CartItem[]=[]

  addItem(item:CartItem){
    this.itens.push(item)
  }
  findItem(product:Product):any{
    return this.itens.find((it)=>it.product===product)

   }


 calculateTotal(){
    let aux=0.0
    this.itens.forEach(it=>aux+=it.total as any)
    this.totalAmount=aux
  }

  toSaleRequest(){
    return {
      total_amount:this.totalAmount
    }
  }

}
