import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMailComponent } from './newComponent-dialog.component';

describe('NewMailComponent', () => {
  let component: NewMailComponent;
  let fixture: ComponentFixture<NewMailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewMailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
