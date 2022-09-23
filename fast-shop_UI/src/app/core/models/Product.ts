import { Link, Page } from "../http/Responses"
import { Category } from "./Category"

export interface Product{
  id:Number
  name:string,
  description:string
  measurement_unit:string
  available_unities: number
  price:Number
  purchise_price:Number
  category:Category
  links:Link[]
}

export interface ProductCollection{
  content:Product[],
  links:Link[]
  page?:Page
}

export interface ProducInput{
 name:string,
 description:String
 measurement_unit:string
 price:Number
 reseller_price:Number
 purchise_price:Number
 category_id:Number
 self_url?:string
}
