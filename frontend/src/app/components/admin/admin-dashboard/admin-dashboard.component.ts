import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  template: `
    <mat-card>
      <mat-card-header>
        <mat-card-title>Admin Dashboard</mat-card-title>
      </mat-card-header>
      <mat-card-content>
        <p>Total Users Registered: {{ totalUsers }}</p>
      </mat-card-content>
    </mat-card>
  `,
  styles: [`
    mat-card {
      margin: 20px;
    }
  `]
})
export class AdminDashboardComponent {
  totalUsers: number = 0;
  // You could load dashboard metrics here using a service
}
