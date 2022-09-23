import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-container-nav',
  templateUrl: './app-container-nav.component.html',
  styleUrls: ['./app-container-nav.component.css']
})
export class AppContainerNavComponent {
  @Input()
  navItens!:any[]

  @Input()
  active!:any

  constructor() { }
}
