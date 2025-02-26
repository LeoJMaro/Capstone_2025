import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePolicyComponent } from './home-policy.component';

describe('HomePolicyComponent', () => {
  let component: HomePolicyComponent;
  let fixture: ComponentFixture<HomePolicyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePolicyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
