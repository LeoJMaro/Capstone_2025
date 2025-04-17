import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../../header/header.component";
import { AutoPolicy } from '../../../shared/models/auto-policy.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { AutoQuoteDisplayComponent } from './auto-quote-display/auto-quote-display.component';
import { AutoPolicyService } from '../../../shared/services/auto-policy.service';
import { Observable} from 'rxjs/internal/Observable';


@Component({
  selector: 'app-auto-policy',
  imports: [
    ReactiveFormsModule,
    MatListModule,
    MatDividerModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    RouterModule,
    MatIcon,
    CommonModule,
    MatButtonModule,
    MatFormFieldModule,

],
  templateUrl: './auto-policy.component.html',
  styleUrl: './auto-policy.component.css'
})
export class AutoPolicyComponent {
    autoForm!: FormGroup;
    autoData!: any;
    quoteData!: object;

    constructor(
      private router: Router,
      private route: ActivatedRoute,
      private dialog: MatDialog,
      private autoPolicyService: AutoPolicyService
    ) {}

      ngOnInit() {
        this.autoForm = new FormGroup({
          vehicleMake: new FormControl('', [Validators.required]),
          vehicleModel: new FormControl('', [Validators.required]),
          vehicleYear: new FormControl('', [Validators.required]),
          vehicleAccidents: new FormControl('', [Validators.required])
        });
      }

    isChildRoute(): boolean {
      return this.router.url.includes('/services');
    }

    onSubmit() {
      this.autoData = this.autoForm.value;

      // Using an observer object for subscription
      this.autoPolicyService.postAutoQuote(this.autoData).subscribe({
        next: (response) => {
          // Handle the response data
          this.quoteData = response;
          console.log("#########################")
          console.log("API RESP IN AUTO COMP:", this.quoteData)

          // Open the quote dialog with the response data
          this.openQuoteDialog(this.autoData, this.quoteData);
        },
        error: (err) => {
          // Handle errors if any
          console.error('Error fetching quote:', err);
        }
      });
    }

    openQuoteDialog(autoData: AutoPolicy, quoteData: any): void {
      this.dialog.open(AutoQuoteDisplayComponent, {
        width: '500px',
        data: { ...autoData, quoteData }
      });
    }
}
