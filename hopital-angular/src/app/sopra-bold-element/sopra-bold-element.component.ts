import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'sopra-bold-element',
  templateUrl: './sopra-bold-element.component.html',
  styleUrls: ['./sopra-bold-element.component.css']
})
export class SopraBoldElementComponent implements OnInit {

  @Input() prefix: string;
  @Input() visite;
  
  
  constructor() { }

  ngOnInit() {
  }

}
