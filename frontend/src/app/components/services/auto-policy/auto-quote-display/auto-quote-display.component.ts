import { Component,Inject } from '@angular/core';
import { AutoPolicy } from '../../../../shared/models/auto-policy.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatList, MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-auto-quote-display',
  imports: [
    MatFormFieldModule,
    MatListModule,
    MatDividerModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './auto-quote-display.component.html',
  styleUrl: './auto-quote-display.component.css'
})
export class AutoQuoteDisplayComponent {

  vehicleMake!: string;
  vehicleModel!: string;
  vehicleYear!: number;
  vehicleAccidents!: number;

  basePremium!: number;
  accidentFactor!: number;
  ageFactor!: number;
  premium!: number;


  constructor(
    public dialogRef: MatDialogRef<AutoQuoteDisplayComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {
    this.vehicleMake = this.data.vehicleMake;
    this.vehicleModel = this.data.vehicleModel;
    this.vehicleYear = this.data.vehicleYear;
    this.vehicleAccidents = this.data.vehicleAccidents;

    this.basePremium = this.data.quoteData.basePremium;
    this.accidentFactor = this.data.quoteData.accidentFactor;
    this.ageFactor = this.data.quoteData.ageFactor;
    this.premium = this.data.quoteData.premium;

    console.log(this.vehicleMake)
    console.log(this.basePremium)

  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}
