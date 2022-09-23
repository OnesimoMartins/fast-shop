import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, take } from 'rxjs';
import { Category, CategoryCollection, CategoryInput } from '../models/Category';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private  categoriesUrl = `${environment.apiUrl}categories`;

 private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

  allCategories(): Observable<CategoryCollection> {
    return this.http.get<CategoryCollection>(this.categoriesUrl, {
      headers: this.httpHeaders,
    });
  }

  getRequestByUrl(url:string):Observable<any>{
    return this.http.get<any>(url,{headers:this.httpHeaders})
  }

  createCategory(category:CategoryInput):Observable<Category>{
    return this.http.post<Category>(this.categoriesUrl,category)
  }

  categories(page: number): Observable<CategoryCollection> {
    const url = this.categoriesUrl + `?size=5page=${page}`;
    return this.http.get<CategoryCollection>(url, {
      headers: this.httpHeaders,
    });
  }

  findCategory(id:number):Observable<Category>{
   return this.http.get<Category>(this.categoriesUrl+`/${id}`,{headers:this.httpHeaders})
  }

  updateCategory(category:CategoryInput,url:string):Observable<Category>{
    return this.http.put<Category>(url,category)
  }

  deleteCategoryByUrl(url: string):Observable<any> {
  return  this.http.delete(url,{headers:this.httpHeaders})

  }


}
