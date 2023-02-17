import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CharityActionRatingComponent } from './charity-action-rating.component';

describe('CharityActionRatingComponent', () => {
  let component: CharityActionRatingComponent;
  let fixture: ComponentFixture<CharityActionRatingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CharityActionRatingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CharityActionRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
