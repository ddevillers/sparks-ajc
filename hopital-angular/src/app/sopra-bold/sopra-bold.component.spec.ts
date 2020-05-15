import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SopraBoldComponent } from './sopra-bold.component';

describe('SopraBoldComponent', () => {
  let component: SopraBoldComponent;
  let fixture: ComponentFixture<SopraBoldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SopraBoldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SopraBoldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
