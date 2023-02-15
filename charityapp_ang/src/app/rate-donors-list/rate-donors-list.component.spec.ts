import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateDonorsListComponent } from './rate-donors-list.component';

describe('RateDonorsListComponent', () => {
  let component: RateDonorsListComponent;
  let fixture: ComponentFixture<RateDonorsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateDonorsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RateDonorsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
