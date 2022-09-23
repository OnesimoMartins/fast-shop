import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { SaleResponse } from 'src/app/core/models/Sale';

@Injectable({
  providedIn: 'root'
})
export class AppInvoiceModalService {
  content:any

 private display:Subject<Boolean>=new Subject<Boolean>()

  constructor() {
    this.display.next(false)
  }

  open( content:SaleResponse){
    this.content=content
    this.display.next(true)
  }

  close(){
    this.display.next(false)
  }

  watch():Observable<Boolean>{
    return this.display.asObservable()
  }

  getContent(){
     return this.content!
  }

}
