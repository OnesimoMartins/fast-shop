import { Component} from '@angular/core';
import { Product, ProductCollection } from 'src/app/core/models/Product';
import { ProductService } from 'src/app/core/services/product.service';

@Component({
  selector: 'app-stock',
  templateUrl: './app-stock.component.html',
  styleUrls: ['./app-stock.component.css']
})
export class AppStockComponent {

  products:ProductCollection={
    content:[],
    links:[],
  }

  constructor(private productService:ProductService) {
    this. productService.getProducts(0).subscribe(it=>{
      this.products=it
    })
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

  increaseQuantity(p:Product,quantity:string ){
    this.productService.increaseStock(p.links[2].href+quantity).subscribe(value=>{
      p.available_unities=  value.available_unities;
    })

  }

}
