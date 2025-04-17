import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AdminDashboardComponent } from '../../admin/admin-dashboard/admin-dashboard.component';

@Component({
  selector: 'app-banner',
  standalone: true,
  imports: [
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
    MatDialogModule
  ],
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent {

  constructor(private dialog: MatDialog) { }

  openAdminDialog(): void {
    this.dialog.open(AdminDashboardComponent, {
      width: '80%',
      height: '80%',
      disableClose: false,
    });
  }
}
