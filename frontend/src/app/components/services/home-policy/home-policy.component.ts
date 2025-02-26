import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-home-policy',
  imports: [
      ReactiveFormsModule,
      MatCardModule,
      MatInputModule,
      MatSelectModule,
      RouterModule,
      MatIcon,
      CommonModule,
      MatButton
  ],
  templateUrl: './home-policy.component.html',
  styleUrl: './home-policy.component.css'
})
export class HomePolicyComponent {

    constructor(private router: Router, private route: ActivatedRoute) {}
  
    isChildRoute(): boolean {
      return this.router.url.includes('/services');
    }
}
