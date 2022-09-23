import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { CategoryInput } from 'src/app/core/models/Category';
import { CategoryService } from 'src/app/core/services/category.service';

@Component({
  selector: 'app-app-category-form',
  templateUrl: './app-category-form.component.html',
  styleUrls: ['./app-category-form.component.css']
})
export class AppCategoryFormComponent implements OnInit{

  navItens=[{text:"Producto",link: "/product/new"},
  {text:"Todos Productos",link:"/products"},
  {text:"Categoria",link:'/category/new'} ,
  {text:"Todas Categorias", link:"/categories"}]

  buttonText:string='Adicionar'

  activeItem=this.navItens[2]

  category:CategoryInput={name:''}
  urlUpdate:string=''

  private id:string;
  constructor(private categoryService:CategoryService,activeRouter:ActivatedRoute
    ,private router:Router) {
   this.id=activeRouter.snapshot.paramMap.get('id')!
  }


  ngOnInit(): void {

    if(this.id){
     this.categoryService.findCategory( this.id as any).subscribe(value=>{
      this.category.name=value.name
      this.urlUpdate=value.links[0].href
      })
      this.buttonText='Actualizar'
    }
  }


  onSubmit(){
    this.id ? this.updateCategory()
     : this.cretateCategory()

     this.router.navigateByUrl("/categories")
  }

  cretateCategory(){
    this.categoryService.createCategory(this.category).subscribe()
  }

  updateCategory(){
    this.categoryService.updateCategory(this.category,this.urlUpdate).subscribe()
}


  cleanField(){
    this.category.name=''
  }

}
