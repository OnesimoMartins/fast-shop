import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/core/models/Cart';
import { SaleResponse } from 'src/app/core/models/Sale';
import { AppInvoiceModalService } from './app-invoice-modal.service';

@Component({
  selector: 'app-invoice-modal',
  templateUrl: './app-invoice-modal.component.html',
  styleUrls: ['./app-invoice-modal.component.css']
})
export class AppInvoiceComponent{

  invoiceInfo={ telefone:"923 567 890", morada:"Belas Shopping, Av 14, Talatona",NIF:20102003, email:'fastShop2022@gmail.co.ao'}

  visible:Boolean=false;

  content!:SaleResponse

  constructor(private modalservice:AppInvoiceModalService) {
    modalservice.watch().subscribe(value=>{this.visible=value
      this.content=this.modalservice.getContent()
    })
  }

  close(){
    this.modalservice.close()
  }

}
