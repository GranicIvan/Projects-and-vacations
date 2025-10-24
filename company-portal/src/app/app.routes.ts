import { Routes } from '@angular/router';
import { authGuard } from './modules/shared/guards/auth-guard';
import { Welcome } from './modules/shared/components/welcome/welcome';

export const routes: Routes = [
  { path: '', component: Welcome },
    // {
  //   path: '',
  //   redirectTo: '/dashboard',
  //   pathMatch: 'full',
  // },
  {
    path: 'vacations',
    loadChildren: () =>
      import('./modules/vacations/vacations-module').then((m) => m.VacationsModule),
    canActivate: [authGuard],
  },
  {
    path: 'employees',
    loadChildren: () =>
      import('./modules/employees/employees-module').then((m) => m.EmployeesModule),
    canActivate: [authGuard],
  },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./modules/shared/components/user-dashboard/user-dashboard').then(
        (m) => m.UserDashboard,
      ),
  },

  {
    path: 'employees/edit/:id',
    loadComponent: () =>
      import('./modules/employees/components/edit-employee/edit-employee').then(
        (m) => m.EditEmployee,
      ),
    canActivate: [authGuard],
  },
  {
    path: 'test-wo-jwt',
    loadComponent: () =>
      import('./modules/testing/components/test-wo-jwt/test-wo-jwt').then((m) => m.TestWoJwt),
  },
  {
    path: '**',
    redirectTo: '/dashboard',
  },
];
