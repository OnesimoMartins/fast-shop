import { Component, OnInit } from '@angular/core';
import { Product, ProductCollection } from 'src/app/core/models/Product';
import { MoneyService } from 'src/app/core/services/money.service';
import { ProductService } from 'src/app/core/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './app-product-list.component.html',
  styleUrls: ['./app-product-list.component.css']
})
export class AppProductListComponent implements OnInit {

  navItens=[{text:"Producto",link: "/product/new"},
  {text:"Todos Productos",link:"/products"},
  {text:"Categoria",link:'/category/new'} ,
  {text:"Todas Categorias", link:"/categories"}]

  active=this.navItens[1]

  products:ProductCollection={
    content:[], links:[]
  }

  constructor(private productService:ProductService,
    private moneyService:MoneyService) { }

  ngOnInit() {
   this. productService.getProducts(0).subscribe(it=>{
    this.products=it
  })
  }

  formatMoney(number:Number):String{
    return this.moneyService.formatMoney(number)
  }


  nextPage() {
    this.products?.links
      .filter((it) => it.rel === 'next')
      .map((ref) => {
        this.productService.getRequestByUrl(ref.href).subscribe((it) => {
          this.products = it;
        });
    });
  }

  previousPage() {
    if (this.products.links[0].rel == 'previous')
      this.productService
        .getRequestByUrl(this.products!.links[0].href)
        .subscribe((it) => {
          this.products = it;
        });
  }

  hasNextPage(): Boolean {
    return this.products?.links.find((it) => it.rel === 'next')
      ? true
      : false;
  }

  isFirst(): Boolean {
    return this.products?.links.find((it) => it.rel === 'previous')
      ? false
      : true;
  }

  deleteProduct(product:Product):void{
    this.productService.deleteRequestByUrl(product.links[1].href).subscribe(it=>{
      const index=this.products.content.indexOf(product)
      this.products.content.splice(index)
    })

  }


}
