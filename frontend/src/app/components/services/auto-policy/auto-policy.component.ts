import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButton } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../../header/header.component";

@Component({
  selector: 'app-auto-policy',
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    RouterModule,
    MatIcon,
    CommonModule,
    MatButton,
],
  templateUrl: './auto-policy.component.html',
  styleUrl: './auto-policy.component.css'
})
export class AutoPolicyComponent {
    constructor(private router: Router, private route: ActivatedRoute) {}
  
    isChildRoute(): boolean {
      return this.router.url.includes('/services');
    }
}
