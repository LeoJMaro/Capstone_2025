import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { ServicesComponent } from './components/services/services.component';
import { ContactComponent } from './components/contact/contact.component';
import { LoginComponent } from './components/login/login.component';
import { HomePolicyComponent } from './components/services/home-policy/home-policy.component';
import { AutoPolicyComponent } from './components/services/auto-policy/auto-policy.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'about', component: AboutComponent },

  // { path: 'services', component: ServicesComponent },
  // { path: 'home-policy', component: HomePolicyComponent },

  { path: 'services', component: ServicesComponent, 
    children: [
      { path: 'home-policy', component: HomePolicyComponent },
      { path: 'auto-policy', component: AutoPolicyComponent }
    ]
  },
      

  { path: 'contact', component: ContactComponent },
  { path: 'login', component: LoginComponent },



];


