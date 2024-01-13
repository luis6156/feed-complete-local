import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './services/auth/guards/auth.guard';
import { LoginGuard } from './services/auth/guards/login.guard';
import { RegisterComponent } from './pages/auth/register/register.component';
import { LoginComponent } from './pages/auth/login/login.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [LoginGuard] },
];
