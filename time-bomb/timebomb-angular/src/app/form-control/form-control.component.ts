import { Component, OnInit, Input, Output, EventEmitter, HostListener } from '@angular/core';

@Component({
  selector: 'form-control',
  templateUrl: './form-control.component.html',
  styleUrls: ['./form-control.component.css']
})
export class FormControlComponent implements OnInit {
  @Input() private id: string;
  @Input() private label: string;
  @Input() private type: string = 'text';
  @Input() private value: any;
  @Output() valueChange = new EventEmitter<string>();
  @Output('enterkeypressed') enterKeyPressed = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit() {
  }

  @HostListener('keydown.enter')
  public onEnterKeyDown() {
    this.enterKeyPressed.emit(true);
  }
}