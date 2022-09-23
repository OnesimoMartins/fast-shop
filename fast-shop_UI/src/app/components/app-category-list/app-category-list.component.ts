import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category, CategoryCollection } from 'src/app/core/models/Category';
import { CategoryService } from 'src/app/core/services/category.service';

@Component({
  selector: 'app-app-category-list',
  templateUrl: './app-category-list.component.html',
  styleUrls: ['./app-category-list.component.css'],
})
export class AppCategoryListComponent implements OnInit {
  navItens = [
    { text: 'Producto', link: '/product/new' },
    { text: 'Todos Productos', link: '/products' },
    { text: 'Categoria', link: '/category/new' },
    { text: 'Todas Categorias', link: '/categories' },
  ];
  activeItem = this.navItens[3];

  categoies?: CategoryCollection = { content: [], links: [] };

  constructor(private categoryService: CategoryService,private router:Router) {}

  ngOnInit(): void {
      this.categoryService.categories(0).subscribe((it) =>this.categoies = it);
  }

  nextPage() {
    this.categoies?.links
      .filter((it) => it.rel === 'next')
      .map((ref) => {
        this.categoryService.getRequestByUrl(ref.href).subscribe((it) => {
          this.categoies = it;
        });
    });
  }

  previousPage() {
    if (this.categoies!.links[0].rel == 'previous')
      this.categoryService
        .getRequestByUrl(this.categoies!.links[0].href)
        .subscribe((it) => {
          this.categoies = it;
        });
  }

  hasNextPage(): Boolean {
    return this.categoies?.links.find((it) => it.rel === 'next')
      ? true
      : false;
  }

  isFirst(): Boolean {
    return this.categoies?.links.find((it) => it.rel === 'previous')
      ? false
      : true;
  }

  deleteCategory(category:Category){
    this.categoryService.deleteCategoryByUrl(category.links[0].href).subscribe(e=>{
      const index=this.categoies?.content.indexOf(category)!
      this.categoies?.content.splice(index)
    })

  }

}
