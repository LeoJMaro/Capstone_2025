import { Component } from '@angular/core';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatError, MatFormField, MatLabel, MatSuffix} from '@angular/material/form-field';
import {MatIcon} from '@angular/material/icon';
import {MatInput} from '@angular/material/input';
import {NgIf} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet} from '@angular/router';
import {RegisterService} from '../../../shared/services/register.service';

@Component({
  selector: 'app-register',
  imports: [
    MatButton,
    MatError,
    MatFormField,
    MatIcon,
    MatIconButton,
    MatInput,
    MatLabel,
    MatSuffix,
    NgIf,
    ReactiveFormsModule,
    RouterOutlet,
    RouterModule,
    RouterLink,
    RouterLinkActive

  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  // firstName: "Dylan",
  // lastName: "Mercer",
  // email: "dm@IloveFeet.com",
  // phone: "123456789",
  // address: "123 Feet Lover Avenue",
  // dateOfBirth: "2002-05-10",
  // createdAt: ""


  firstName!: string;
  lastName!:string;
  email!:string;
  phone!:number;
  address!:string;
  dateOfBirth!:string
  createdAt!:"";

  registerData: any;
  hidePassword = true;
  registerForm!: FormGroup;
  registerResponse!: any;

  constructor( private router: Router, private registerService: RegisterService){
  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required])
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      console.log(this.registerForm.value);
      this.registerData = this.registerForm.value;

      this.registerService.postCustomer(this.registerData).subscribe({
        next: (response) => {
          // Handle the response data
          this.registerResponse = response;
          console.log("#########################")
          console.log("API RESP IN AUTO COMP:", this.registerResponse)

        },
        error: (err) => {
          // Handle errors if any
          console.error('Error fetching quote:', err);
        }
      });

      this.router.navigate(['/login']);
    } else {
      console.log('Form is invalid');
      this.registerForm.markAllAsTouched();
    }
  }

}
