import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { HomePolicyComponent } from "./home-policy/home-policy.component";
import { CommonModule } from '@angular/common';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-services',
  imports: [
    RouterModule,
    RouterLink,
    RouterLinkActive,
    CommonModule,
    MatButton
],
  templateUrl: './services.component.html',
  styleUrl: './services.component.css'
})
export class ServicesComponent {


  constructor(private router: Router, private route: ActivatedRoute) {}

  isChildRoute(): boolean {
    return this.router.url.includes('/home-policy') || this.router.url.includes('/auto-policy');
  }
  
}
