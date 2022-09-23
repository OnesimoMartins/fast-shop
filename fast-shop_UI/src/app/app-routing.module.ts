import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppCategoryFormComponent } from "./components/app-category-form/app-category-form.component";
import { AppCategoryListComponent } from "./components/app-category-list/app-category-list.component";
import { AppInvoiceComponent } from "./components/app-invoice/app-invoice-modal.component";
import { AppProductFormComponent } from "./components/app-product-form/app-product-form.component";
import { AppProductListComponent } from "./components/app-product-list/app-product-list.component";
import { AppSaleComponent } from "./components/app-sale/app-sale.component";
import { AppShoppingCartComponent } from "./components/app-shopping-cart/app-shopping-cart.component";
import { AppStockComponent } from "./components/app-stock/app-stock.component";

const routes:Routes=[{path:"sale/chart", component:AppShoppingCartComponent},
{path:"sale", component:AppSaleComponent},{path:"products", component:AppProductListComponent}
,{path:"stock", component:AppStockComponent},
 { path:"product/new",component:AppProductFormComponent},
 { path:"product/update/:id",component:AppProductFormComponent},
 { path:"sale/invoice",component:AppInvoiceComponent},
 { path:"category/new",component:AppCategoryFormComponent},
 { path:"category/update/:id",component:AppCategoryFormComponent},
 { path:"categories",component:AppCategoryListComponent},
 { path:"",component:AppSaleComponent}


]

@NgModule({
  declarations:[],
  imports:[RouterModule.forRoot(routes)],
  exports:[RouterModule]
})
export class AppRoutingModule{}
