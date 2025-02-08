import { Component } from '@angular/core';
import { BannerComponent } from './banner/banner.component';
import { NavbarComponent } from "./navbar/navbar.component";

@Component({
  selector: 'app-header',
  imports: [
    BannerComponent,
    NavbarComponent
],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
