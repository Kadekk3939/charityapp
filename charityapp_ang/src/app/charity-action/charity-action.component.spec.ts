import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CharityActionComponent } from './charity-action.component';

describe('CharityActionComponent', () => {
  let component: CharityActionComponent;
  let fixture: ComponentFixture<CharityActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CharityActionComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CharityActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
