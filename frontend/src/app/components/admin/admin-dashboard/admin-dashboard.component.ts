import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule, MatCardTitle } from '@angular/material/card';
import {ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from '../../login/login.component';



@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, MatCardModule, ReactiveFormsModule, LoginComponent, MatCardTitle],
  templateUrl: './admin-dashboard.component.html',

})
export class AdminDashboardComponent {
  totalUsers = 1


}
