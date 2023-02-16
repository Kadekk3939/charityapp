import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorsDonationHistoryComponent } from './donors-donation-history.component';

describe('DonorsDonationHistoryComponent', () => {
  let component: DonorsDonationHistoryComponent;
  let fixture: ComponentFixture<DonorsDonationHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonorsDonationHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DonorsDonationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
