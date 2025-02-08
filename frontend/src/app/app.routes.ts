import { Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { ServicesComponent } from './components/services/services.component';
// import { Service1Component } from './components/services/service1/service1.component';
// import { Service2Component } from './components/services/service2/service2.component';
// import { Service3Component } from './components/services/service3/service3.component';
// import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { ContactComponent } from './components/contact/contact.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'services', component: ServicesComponent },
//   { path: 'services/service1', component: Service1Component },
//   { path: 'services/service2', component: Service2Component },
//   { path: 'services/service3', component: Service3Component },
//   { path: 'portfolio', component: PortfolioComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'login', component: LoginComponent },
];

