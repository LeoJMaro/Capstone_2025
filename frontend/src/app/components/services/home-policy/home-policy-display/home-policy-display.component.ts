import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-home-policy-display',
  imports: [
        MatFormFieldModule,
        MatListModule,
        MatDividerModule,
        MatButtonModule,
        MatCardModule
  ],
  templateUrl: './home-policy-display.component.html',
  styleUrl: './home-policy-display.component.css'
})
export class HomeQuotePolicyDisplayComponent {

    dwellingType!: string;
    heatingType!: string;
    location!: string;
    age!: number;
    homeValue!: number

    basePremium!: number;
    ageFee!: number;
    heatingFee!: number;
    locationFee!: number;
    additional!: number;
    discountAmount!: number;
    premium!: number;

    constructor(
      public dialogRef: MatDialogRef<HomeQuotePolicyDisplayComponent>,
      @Inject(MAT_DIALOG_DATA) public data: any
    ) {}

    ngOnInit() {

      this.dwellingType = this.data.dwellingType;
      this.heatingType = this.data.heatingType;
      this.location = this.data.location;
      this.age = this.data.age;
      this.homeValue = this.data.homeValue;

      this.basePremium = this.data.quoteData.basePremium;
      this.premium = this.data.quoteData.premium;
    }

    closeDialog(): void {
      this.dialogRef.close();
    }
}
