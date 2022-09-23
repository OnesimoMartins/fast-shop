import { Component, EventEmitter, Input, OnInit } from '@angular/core';
import PageController from 'src/app/core/http/PageController';

@Component({
  selector: 'app-pagination',
  templateUrl: './app-pagination.component.html',
  styleUrls: ['./app-pagination.component.css']
})
export class AppPaginationComponent implements OnInit {

  @Input()
  pageControl!:PageController

  pageNavigation=new EventEmitter()

  constructor() { }

  ngOnInit() {}


  nextPage(){
      this.pageNavigation.emit(this.pageControl.next)
  }

  previousPage(){
  //   this. productService.getProducts(this.pageControl.previous).subscribe(it=>{
  //     this.products=it
  //     this.updatePageControl()
    // })

  //   this.updatePageControl()
  }

  // updatePageControl(){

  //   this.pageControl.current=this.products.page.number

  //   this.pageControl.isFirst=this.pageControl.current==0

  //   this.pageControl.isLast= this.products.page.number==(this.products.page.totalPages-1)

  //   if(this.pageControl.current+1!=this.products.page.totalPages)
  //      this.pageControl.next=this.pageControl.current +1

  //   if(this.pageControl.current>0)
  //       this.pageControl.previous=this.pageControl.current -1

  //       console.log(this.pageControl);
  //     console.log(this.products.page);

        // console.log(this.products);

  }

