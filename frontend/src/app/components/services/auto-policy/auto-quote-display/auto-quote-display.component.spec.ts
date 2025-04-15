import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutoQuoteDisplayComponent } from './auto-quote-display.component';

describe('AutoQuoteDisplayComponent', () => {
  let component: AutoQuoteDisplayComponent;
  let fixture: ComponentFixture<AutoQuoteDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutoQuoteDisplayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutoQuoteDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
