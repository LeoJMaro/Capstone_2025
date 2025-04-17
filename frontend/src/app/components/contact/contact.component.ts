import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators, FormBuilder} from '@angular/forms';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-contact',
  // Using standalone component approach (Angular 18)
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatListModule,
    MatDividerModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    RouterModule,
    MatIconModule,
    CommonModule,
    MatButtonModule,
    MatFormFieldModule,
    HeaderComponent,
    FormsModule
  ],
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']  // Changed from styleUrl to styleUrls
})
export class ContactComponent implements OnInit {
  contactForm!: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    // Initialize the form with controls matching the template's formControlName values
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required]   // Add the phone control here
    });
  }

  onSubmit(): void {
    if (this.contactForm.valid) {
      console.log('Form submitted:', this.contactForm.value);
      // Reset form with explicit value clearing
      this.contactForm.reset({
        name: '',
        email: '',
        phone: ''
      });
    }
  }
}
