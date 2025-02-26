import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutoPolicyComponent } from './auto-policy.component';

describe('AutoPolicyComponent', () => {
  let component: AutoPolicyComponent;
  let fixture: ComponentFixture<AutoPolicyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutoPolicyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutoPolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
