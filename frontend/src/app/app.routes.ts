import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {AboutComponent} from './components/about/about.component';
import {AdminDashboardComponent} from './components/admin/admin-dashboard/admin-dashboard.component';
import {UserManagementComponent} from './components/admin/user-management/user-management.component';
import {ServicesComponent} from './components/services/services.component';
import {ContactComponent} from './components/contact/contact.component';
import {LoginComponent} from './components/login/login.component';
import {HomePolicyComponent} from './components/services/home-policy/home-policy.component';
import {AutoPolicyComponent} from './components/services/auto-policy/auto-policy.component';
import {ManagePoliciesComponent} from './components/services/manage-policies/manage-policies.component';
import {RegisterComponent} from './components/login/register/register.component';

export const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'about', component: AboutComponent},

  // { path: 'services', component: ServicesComponent },
  // { path: 'home-policy', component: HomePolicyComponent },

  {
    path: 'services', component: ServicesComponent,
    children: [
      {path: 'home-policy', component: HomePolicyComponent},
      {path: 'auto-policy', component: AutoPolicyComponent},

    ]
  },
  {path: 'manage-policies', component: ManagePoliciesComponent},

  {path: 'contact', component: ContactComponent},
  {path: 'login', component: LoginComponent},

  {path: 'register', component: RegisterComponent},

  {
    path: 'about/dashboard', component: AdminDashboardComponent}
  ,
  { path: 'dashboard', component: AdminDashboardComponent,

  },
  {
    path: 'services/dashboard', component: AdminDashboardComponent,
  },
  {
    path: 'contact/dashboard', component: AdminDashboardComponent,
  },
  {
    path: 'users/dashboard', component: AdminDashboardComponent,
  },  {
    path: 'manage-policies/dashboard', component: AdminDashboardComponent,
  },
  {
    path: 'login/dashboard', component: AdminDashboardComponent,
  },
  {
    path: 'about/dashboard', component: AdminDashboardComponent,
  },
  {path: 'users', component: UserManagementComponent,
  },
];


