import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagePoliciesComponent } from './manage-policies.component';

describe('ManagePoliciesComponent', () => {
  let component: ManagePoliciesComponent;
  let fixture: ComponentFixture<ManagePoliciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagePoliciesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagePoliciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
