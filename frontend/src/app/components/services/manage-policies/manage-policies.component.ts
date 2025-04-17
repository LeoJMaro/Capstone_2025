import { Component } from '@angular/core';
import { MatCard, MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button'; // For buttons (Renew, Cancel)
import { MatPaginatorModule } from '@angular/material/paginator'; // For pagination (if required)
import { MatSortModule } from '@angular/material/sort'; // For sorting (if required)
import { Router } from '@angular/router';
import { HeaderComponent } from '../../header/header.component';
import { AutoPolicyService } from '../../../shared/services/auto-policy.service';
import { HomePolicyService } from '../../../shared/services/home-policy.service';


@Component({
  selector: 'app-manage-policies',
  imports: [
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatPaginatorModule,
    MatSortModule,
    HeaderComponent

  ],
  templateUrl: './manage-policies.component.html',
  styleUrl: './manage-policies.component.css'
})
export class ManagePoliciesComponent {

  policyData!: any;
  autoData!: any;
  homeData!: any;
  userData!: any;

  displayedColumnsHome: string [] = ['homeType','homeAge','location', 'heatingType','homeValue', 'startDate','endDate', 'actions']
  displayedColumnsAuto: string[] = [ 'vehicleMake','vehicleModel','vehicleYear', 'accidentCount', 'premium','startDate', 'endDate', 'actions'];
  // homeType!: string;
  // homeAge!: number;
  // heatingType!: string;
  // homeValue!: number;

  constructor(
    private router: Router,
    private autoPolicyService: AutoPolicyService,
    private homePolicyService: HomePolicyService,
  ) {

  }

  ngOnInit() {
    this.autoPolicyService.getAutoPolicyById(1).subscribe({
      next: (response) => {
        // Handle the response data
        this.autoData = response;
        console.log("policy DATA IN MANAGE",this.autoData)
        // this.policyFilter(this.policyData);
      },
      error: (err) => {
        // Handle errors if any
        console.error('Error fetching quote:', err);
      }
    });

    this.homePolicyService.getHomePolicyById(1).subscribe({
      next: (response) => {
        // Handle the response data
        this.homeData = response;
        console.log("HOME DATA IN MANAGE:", this.homeData)
        // this.homeFilter();
      },
      error: (err) => {
        // Handle errors if any
        console.error('Error fetching quote:', err);
      }
    });


  }


  isChildRoute(): boolean {
    return this.router.url.includes('/manage-policies')
  }




  // policyFilter(policyData: any) {
  //   for (let policy of policyData) {
  //     console.log(policy)
  //     if (policy.policyType === "AUTO") {
  //       this.autoData.push(policy)
  //     } else if (policy.policyType === "HOME") {
  //       this.homeData.push(policy)
  //
  //     }
  //   }
  //   console.log("POLICY FILTER Home RESULT:", this.homeData);
  //   console.log("POLICY FILTER AUto RESULT:", this.autoData)
  // }




  renewPolicy(policy: any) {
    console.log('Renewing policy:', policy);
  }

  cancelPolicy(policy: any) {
    console.log('Canceling policy:', policy);
  }
}
