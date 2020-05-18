import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisiteCrudRowComponent } from './visite-crud-row.component';

describe('VisiteCrudRowComponent', () => {
  let component: VisiteCrudRowComponent;
  let fixture: ComponentFixture<VisiteCrudRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisiteCrudRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisiteCrudRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
