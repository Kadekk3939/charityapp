import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CharityActionAplicationListComponent } from './charity-action-aplication-list.component';

describe('CharityActionAplicationListComponent', () => {
  let component: CharityActionAplicationListComponent;
  let fixture: ComponentFixture<CharityActionAplicationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CharityActionAplicationListComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CharityActionAplicationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
