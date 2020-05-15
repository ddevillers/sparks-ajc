import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SopraBoldElementComponent } from './sopra-bold-element.component';

describe('SopraBoldElementComponent', () => {
  let component: SopraBoldElementComponent;
  let fixture: ComponentFixture<SopraBoldElementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SopraBoldElementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SopraBoldElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
