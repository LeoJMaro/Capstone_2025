import { CommonModule } from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {AdminService} from '../../../shared/services/admin.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  totalUsers: number = 0;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    // Example: Fetch total users from the backend.
    this.adminService.getTotalUsers().subscribe({
      next: (count: number) => {
        this.totalUsers = count;
      },
      error: (error) => {
        console.error('Error fetching total users', error);
      }
    });
  }
}
