import { Routes } from '@angular/router';
import { Welcome } from './modules/shared/components/welcome/welcome';
import { adminGuard } from './modules/shared/guards/admin-guard';
import { authGuard } from './modules/shared/guards/auth-guard';
import { UserDashboard } from './modules/shared/components/user-dashboard/user-dashboard';

export const routes: Routes = [
  { path: '', component: UserDashboard },

  {
    path: 'vacations',
    runGuardsAndResolvers: 'always',
    loadChildren: () =>
      import('./modules/vacations/vacations-module').then((m) => m.VacationsModule),
    canActivate: [authGuard],
  },
  {
    path: 'employees',
    runGuardsAndResolvers: 'always',
    loadChildren: () =>
      import('./modules/employees/employees-module').then((m) => m.EmployeesModule),
    canActivate: [authGuard],
  },
  {
    path: 'projects',
    runGuardsAndResolvers: 'always',
    loadChildren: () =>
      import('./modules/projects/projects-module').then((m) => m.ProjectsModule),
    canActivate: [authGuard],
  },
  { 
    path: 'monthly-log',
    runGuardsAndResolvers: 'always',
    loadChildren: () =>
      import('./modules/monthly-log/monthly-log-module').then((m) => m.MonthlyLogModule),
    canActivate: [authGuard],
  },
  {
    path: 'dashboard',
    runGuardsAndResolvers: 'always',
    loadComponent: () =>
      import('./modules/shared/components/user-dashboard/user-dashboard').then(
        (m) => m.UserDashboard,
      ),
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
