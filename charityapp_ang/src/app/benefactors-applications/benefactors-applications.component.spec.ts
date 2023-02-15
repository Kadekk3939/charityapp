import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenefactorsApplicationsComponent } from './benefactors-applications.component';

describe('BenefactorsApplicationsComponent', () => {
  let component: BenefactorsApplicationsComponent;
  let fixture: ComponentFixture<BenefactorsApplicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BenefactorsApplicationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BenefactorsApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
