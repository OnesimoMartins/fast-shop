import {  Component,  OnInit,} from '@angular/core';
import { Cart } from 'src/app/core/models/Cart';
import { Product, ProductCollection } from 'src/app/core/models/Product';
import { CartService } from 'src/app/core/services/cart.service';
import { MoneyService } from 'src/app/core/services/money.service';
import { ProductService } from 'src/app/core/services/product.service';

@Component({
  selector: 'app-sale',
  templateUrl: './app-sale.component.html',
  styleUrls: ['./app-sale.component.css'],
})
export class AppSaleComponent implements OnInit {
  navItens = [ { text: 'Productos', link: '/sale' }, { text: 'Carrinho', link: '/sale/chart' },]
  active = this.navItens[0];
  products: ProductCollection = {
    content: [],
    links: [],
  };

  cart!: Cart;

  constructor(
    private productService: ProductService,
    private cartSevice: CartService,
    private moneyService:MoneyService
  ) {}


  ngOnInit(): void {
    this.productService.getAvalilableProducts(0).subscribe((it) =>{
      this.products = it

      it.content.forEach( (product)=>{
        const cartItem=this.cartSevice.getCart().findItem(product)
        if(cartItem) this.cart.addItem(cartItem)
        });

  });

  }

  formatMoney(number:Number){
    return this.moneyService.formatMoney(number)
  }

  // NOT IMPLEMENTED YET--load cart itens
  getProductQuantity(product:Product):number{
    const item=this.cart.findItem(product)?.quantity
 return 1;
  }

  addToCart(product: Product, quantity: any) {
    quantity = quantity as number;
    this.cartSevice.addToCart(product, quantity);
  }

  getTotalItemAmount(product: Product): Number {
    return this.cartSevice.getTotalItemAmount(product) as any;
  }

  nextPage() {
    this.products?.links
      .filter((it) => it.rel === 'next')
      .map((ref) => {
        this.productService.getRequestByUrl(ref.href).subscribe(it => {
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
    return this.products?.links.find((it) => it.rel === 'next') ? true : false;
  }

  isFirst(): Boolean {
    return this.products?.links.find((it) => it.rel === 'previous')
      ? false
      : true;
  }
}
