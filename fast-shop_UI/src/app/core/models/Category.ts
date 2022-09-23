import { Link } from "../http/Responses"

export interface CategoryInput{
  name:string
}

export interface Category{
  id:number,
  name:string,
  links:Link[]
}

export interface CategoryCollection{
  links:Link[],
  content:Category[]
}
