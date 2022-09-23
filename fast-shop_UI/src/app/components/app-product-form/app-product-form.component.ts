import { Component, OnInit } from '@angular/core';
import { NgForm, NgModel } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MeasurementUnitie } from 'src/app/core/http/Responses';
import { Category } from 'src/app/core/models/Category';
import { ProducInput} from 'src/app/core/models/Product';
import { CategoryService } from 'src/app/core/services/category.service';
import { MeasurementService } from 'src/app/core/services/measurement.service';
import { ProductService } from 'src/app/core/services/product.service';



@Component({
  selector: 'app-product-form',
  templateUrl: './app-product-form.component.html',
  styleUrls: ['./app-product-form.component.css'],
})
export class AppProductFormComponent implements OnInit {


 navItens=[{text:"Producto",link: "/product/new"},
  {text:"Todos Productos",link:"/products"},
  {text:"Categoria",link:'/category/new'} ,
  {text:"Todas Categorias", link:"/categories"}]
  activeItem=this.navItens[0]


  product: ProducInput = {
    name: '',
    description: '',
    measurement_unit: 'seleccione',
    price: 0.0,
    purchise_price: 0.0,
    reseller_price: 0.0,
    category_id: 0,
  };

  btnText?: string = 'Adicionar';
  categories: Category[] = [];
  measurementUnities: MeasurementUnitie[] = [];

  productId!:string

  constructor(
    private categoryService: CategoryService,
    private measurementService: MeasurementService,
    private productService: ProductService,
    private activatedRoute:ActivatedRoute,
    private router:Router
  ) {}

  ngOnInit() {
    this.getCategories();
    this.getMeasurementUnities();
    this.productId=this.activatedRoute.snapshot.paramMap.get('id')!;
    if (this.productId) {
      this.fillProductForm(this.productId)
    }

  }

  fillProductForm(id:string){
    this.productService.finOne(id).subscribe(v=>{
      this.product.name=v.name
      this.product.description=v.description
      this.product.category_id=v.category.id
      this.product.measurement_unit=v.measurement_unit
      this.product.price=v.price
      this.product.purchise_price=v.purchise_price
      this.product.self_url=v.links[1].href
    })

    this.btnText='Actualizar'
  }

  onSubmit() {

    this.productId? this.productService.updateProduct(this.product.self_url!,this.product)
    .subscribe( )
    :this.productService.saveProduct(this.product);

    this.router.navigateByUrl('/products')
  }

  resetForm(form: NgForm) {
    // form.reset()
    this.product={
      name: '',
      description: '',
      measurement_unit: 'seleccione',
      price: 0.0,
      purchise_price: 0.0,
      reseller_price: 0.0,
      category_id: 0,
    };
  }

  getCategories() {
    this.categoryService.allCategories().subscribe((response) => {
      this.categories = response.content;
    });
  }

  getMeasurementUnities() {
    this.measurementService.getAllMeasurements().subscribe((it) => {
      this.measurementUnities = it;
    });
  }

}
