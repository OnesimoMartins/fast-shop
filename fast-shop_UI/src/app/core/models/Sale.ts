import { Link } from "../http/Responses"
import { Product } from "./Product"

export interface SaleResponse{
// headers:any
 id:number
 status:string
 payment_mode:string
 total:number
 itens:ItemResponse[]
 links:Link
 amount_paid:number
 transshipment:number
}

export interface SaleRequest{
  payment_mode:String
  itens:Item[]
}

export interface Item{
  product_id:Number
  quantity:number
}

export interface ItemResponse{
  id:number
  quantity:number
  total:number
  product:Product
  links:Link[]
}
