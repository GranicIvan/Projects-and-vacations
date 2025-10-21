import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./modules/shared/components/user-dashboard/user-dashboard').then(m => m.UserDashboard)
  },
  {
    path: 'employees',
    loadComponent: () => import('./modules/employees/components/employees/employees').then(m => m.Employees)
  },
  {
    path: 'employees/new',
    loadComponent: () => import('./modules/employees/components/new-employee/new-employee').then(m => m.NewEmployee)
  },
  {
    path: 'employees/edit/:id',
    loadComponent: () => import('./modules/employees/components/edit-employee/edit-employee').then(m => m.EditEmployee)
  },
  {
    path: 'test-wo-jwt',
    loadComponent: () => import('./modules/testing/components/test-wo-jwt/test-wo-jwt').then(m => m.TestWoJwt)
  },
  {
    path: '**',
    redirectTo: '/dashboard'
  }
];
