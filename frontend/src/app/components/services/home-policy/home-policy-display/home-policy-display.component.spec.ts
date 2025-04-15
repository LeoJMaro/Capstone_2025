import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePolicyDisplayComponent } from './home-policy-display.component';

describe('HomePolicyDisplayComponent', () => {
  let component: HomePolicyDisplayComponent;
  let fixture: ComponentFixture<HomePolicyDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePolicyDisplayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePolicyDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
