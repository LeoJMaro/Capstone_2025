import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatError, MatFormFieldModule } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {RegisterService} from '../../shared/services/register.service';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIcon,
    MatError,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  hidePassword = true;
  loginForm!: FormGroup;
  emailResponse!: any;

  constructor(private router: Router , private registerService: RegisterService) {}

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value);

      this.registerService.getCustomerIdByEmail(this.loginForm.value).subscribe({
        next: (response) => {
          // Handle the response data
          this.emailResponse = response;
          console.log("#########################")
          console.log("API RESP IN EMAIL COMP:", this.emailResponse)

          localStorage.setItem('emailResponse', JSON.stringify(this.emailResponse));


        },
        error: (err) => {
          // Handle errors if any
          console.error('Error fetching quote:', err);
        }
      })



      this.router.navigate(['/services']);
    } else {
      console.log('Form is invalid');
      this.loginForm.markAllAsTouched();
    }
  }

  toRegister() {
    this.router.navigate(['/register'])
  }

  getEmailErrorMsg() {
    const emailControl = this.loginForm.get('email');
    if(emailControl?.hasError("required")) {
      return "Email is required";
    } else if(emailControl?.hasError("email")) {
      return "Invalid email"
    } else {
      return
    }
  }
  getPasswordErrorMsg() {
    const passwordControl = this.loginForm.get('password');
    if(passwordControl?.hasError("required")) {
      return "Password is required"
    } else if(passwordControl?.hasError("minlength")) {
      return "Minimum 6 characters"
    } else {
      return
    }
  }




}
