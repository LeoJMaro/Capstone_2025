import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-auto-policy',
  imports: [
          ReactiveFormsModule,
          MatCardModule,
          MatInputModule,
          MatSelectModule,
          RouterModule
  ],
  templateUrl: './auto-policy.component.html',
  styleUrl: './auto-policy.component.css'
})
export class AutoPolicyComponent {

}
