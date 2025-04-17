import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatButton } from '@angular/material/button';
import { HeaderComponent } from '../../header/header.component';
import { MatDialog } from '@angular/material/dialog';
import { AutoPolicy } from '../../../shared/models/auto-policy.model';
import { AutoPolicyService } from '../../../shared/services/auto-policy.service';
import { AutoQuoteDisplayComponent } from '../auto-policy/auto-quote-display/auto-quote-display.component';
import { HomePolicyService } from '../../../shared/services/home-policy.service';
import { HomeQuotePolicyDisplayComponent } from './home-policy-display/home-policy-display.component';
import { HomePolicy } from '../../../shared/models/home-policy.model';

@Component({
  selector: 'app-home-policy',
  imports: [
      ReactiveFormsModule,
      MatCardModule,
      MatInputModule,
      MatSelectModule,
      RouterModule,
      MatIcon,
      CommonModule,
      MatButton,
  ],
  templateUrl: './home-policy.component.html',
  styleUrl: './home-policy.component.css'
})
export class HomePolicyComponent {

  homeForm!: FormGroup;
  homeData!: any;
  quoteData!: object;


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private homePolicyService: HomePolicyService
  ) {}

  ngOnInit() {

  // private String dwellingType;
  // private String heatingType;
  // private String location;
  // private int age;
  // private double homeValue;

    this.homeForm = new FormGroup({
      dwellingType: new FormControl('', [Validators.required]),
      heatingType: new FormControl('', [Validators.required]),
      location: new FormControl('', [Validators.required]),
      age: new FormControl('', [Validators.required]),
      homeValue: new FormControl('', [Validators.required])
    });
  }

  isChildRoute(): boolean {
    return this.router.url.includes('/services');
  }

  onSubmit() {
    this.homeData = this.homeForm.value;
    console.log("HIT HOME ONSUBMIT")
    // Using an observer object for subscription
    this.homePolicyService.postHomeQuote(this.homeData).subscribe({
      next: (response) => {
        // Handle the response data
        this.quoteData = response;
        console.log("#########################")
        console.log("API RESP IN Home COMP:", this.quoteData)

        // Open the quote dialog with the response data
        this.openQuoteDialog(this.homeData, this.quoteData);
      },
      error: (err) => {
        // Handle errors if any
        console.error('Error fetching quote:', err);
      }
    });
  }

  openQuoteDialog(homeData: HomePolicy, quoteData: any): void {
    console.log("$$$$$$$$$$$$$$$$$$$$")
    console.log("BACK IN ")
    console.log(this.quoteData)
    console.log("$$$$$$$$$$$$$$$$$$$$")
    this.dialog.open(HomeQuotePolicyDisplayComponent, {
      width: '500px',
      data: { ...homeData, quoteData }
    });
  }

}
