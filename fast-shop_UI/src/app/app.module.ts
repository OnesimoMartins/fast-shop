import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule, BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { AppNavBarComponent } from './components/shared/app-nav-bar/app-nav-bar.component';
import { AppProductFormComponent } from './components/app-product-form/app-product-form.component';
import { AppProductListComponent } from './components/app-product-list/app-product-list.component';
import { AppContainerNavComponent } from './components/shared/app-container-nav/app-container-nav.component';
import { AppSaleComponent } from './components/app-sale/app-sale.component';
import { AppStockComponent } from './components/app-stock/app-stock.component';
import { AppShoppingCartComponent } from './components/app-shopping-cart/app-shopping-cart.component';
import { AppPaginationComponent } from './components/shared/app-pagination/app-pagination.component';
import { AppInvoiceComponent } from './components/app-invoice/app-invoice-modal.component';
import { AppCategoryFormComponent } from './components/app-category-form/app-category-form.component';
import { AppCategoryListComponent } from './components/app-category-list/app-category-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AppNavBarComponent,
    AppProductFormComponent,
    AppProductListComponent,
    AppContainerNavComponent,
    AppSaleComponent,
    AppStockComponent,
    AppShoppingCartComponent,
    AppPaginationComponent,
    AppInvoiceComponent,
    AppCategoryFormComponent,
    AppCategoryListComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    BrowserAnimationsModule,
    FormsModule,AppRoutingModule,HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
