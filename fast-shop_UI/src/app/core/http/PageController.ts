export default interface PageController{
  previous:number
  next:number
  current:number
  isFirst:boolean
  isLast:boolean
  totalPages?:number
}
