import { Component, HostListener, OnInit } from '@angular/core';

@Component({
  selector: 'sopra-bold, [sopra-bold]',
  templateUrl: './sopra-bold.component.html',
  styleUrls: ['./sopra-bold.component.css']
})
export class SopraBoldComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @HostListener('click')
  peuImporteSonNom() {
    alert('à Malibu');
  }
}
