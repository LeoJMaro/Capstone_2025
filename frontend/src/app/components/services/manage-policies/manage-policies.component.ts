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

  autoData!: any;
  homeData!: any;
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
    this.autoPolicyService.getAutoPolicies().subscribe({
      next: (response) => {
        // Handle the response data
        this.autoData = response;
      },
      error: (err) => {
        // Handle errors if any
        console.error('Error fetching quote:', err);
      }
    });

    this.homePolicyService.getHomeQuote().subscribe({
      next: (response) => {
        // Handle the response data
        this.homeData = response;
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

  displayedColumnsAuto: string[] = [ 'vehicleMake','vehicleModel','vehicleYear','driverAge', 'accidentCount', 'premium','startDate', 'endDate', 'actions'];
  autoPolicies = [
    {vehicleMake:"Ford", vehicleModel: "Fusion", vehicleYear: "2005", driverAge: 25, accidentCount: 2, premium: 1200, startDate: "", endDate: "" },
    { vehicleMake:"Honda", vehicleModel: "Accord", vehicleYear: "2015", driverAge: 35, accidentCount: 1,  premium: 950, startDate: "", endDate: "" },
    // Add more policies as required
  ];

  displayedColumnsHome: string [] = ['homeType','homeAge','location', 'heatingType','homeValue', 'startDate','endDate', 'actions']
  homePolicies = [
    {homeType: "Bungalow", homeAge: "30", location: "Rural", heatingType: "oil", homeValue: 300000, startDate:'', endDate: ''}
  ]


  renewPolicy(policy: any) {
    console.log('Renewing policy:', policy);
  }

  cancelPolicy(policy: any) {
    console.log('Canceling policy:', policy);
  }
}
