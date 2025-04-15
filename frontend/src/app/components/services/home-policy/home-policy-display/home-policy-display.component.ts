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

    homeType!: string;
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
    quoteAmount!: number;
  
    constructor(
      public dialogRef: MatDialogRef<HomeQuotePolicyDisplayComponent>,
      @Inject(MAT_DIALOG_DATA) public data: any
    ) {}
  
    ngOnInit() {

      this.homeType = this.data.homeType;
      this.heatingType = this.data.heatingType;
      this.location = this.data.location;
      this.age = this.data.age;
      this.homeValue = this.data.homeValue;

      this.basePremium = this.data.quoteData.basePremium;
      this.ageFee = this.data.quoteData.ageFee;
      this.heatingFee = this.data.quoteData.heatingFee;
      this.locationFee = this.data.quoteData.locationFee;
      this.additional = this.data.quoteData.additional;
      this.discountAmount = this.data.quoteData.discountAmount;
      this.quoteAmount = this.data.quoteData.quoteAmount;

  
      console.log(this.location)
      console.log(this.basePremium)
  
  
  
    }
  
    closeDialog(): void {
      this.dialogRef.close();
    }
}
