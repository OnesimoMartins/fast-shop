import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProducInput, Product, ProductCollection } from '../models/Product';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  httpHeaders=new HttpHeaders({ 'Content-Type': 'application/json' })

  url = `${environment.apiUrl}products`

  constructor( private http:HttpClient) { }

  increaseStock(url:string):Observable<Product>{
   return this.http.put<Product>(url,{ headers:this.httpHeaders})
  }

  updateProduct(url:string,product:ProducInput):Observable<Product>{
    return this.http.put<Product>(url,product)
  }

  finOne(id:string):Observable<Product>{
  return  this.http.get<Product>(`${this.url}/${id}`,{headers: this.httpHeaders})

  }

  saveProduct(product:ProducInput){
    this.http.post<ProducInput>(this.url,product).forEach(console.log);
  }

  getProducts(page?:number,size?:number):Observable<ProductCollection>{
   let urlAux=this.url
    if(!page) page=0
    if(!size) size=5

    urlAux+=`?page=${page}`

    urlAux+=`&size=${size}`

    return this.http.get<ProductCollection>(urlAux,{headers:this.httpHeaders})
  }
  getAvalilableProducts(page?:number,size?:number):Observable<ProductCollection>{
    let urlAux=this.url
     if(!page) page=0
     if(!size) size=5

     urlAux+=`?page=${page}`

     urlAux+=`&size=${size}&available=true`

     return this.http.get<ProductCollection>(urlAux,{headers:this.httpHeaders})
   }

  getRequestByUrl(url:string):Observable<any>{
    return this.http.get<any>(url,{headers:this.httpHeaders})
  }

  deleteRequestByUrl(url:string):Observable<any>{
    return this.http.delete<any>(url,{headers:this.httpHeaders})
  }

}
