import { Routes } from '@angular/router';

export const routes: Routes = [
      {
    path: 'test-wo-jwt',
    loadComponent: () =>
      import('./modules/testing/components/test-wo-jwt/test-wo-jwt')
        .then(m => m.TestWoJwt)
  },
        {
    path: 'user-dashboard',
    loadComponent: () =>
      import('./modules/shared/components/user-dashboard/user-dashboard')
        .then(m => m.UserDashboard)
  }
];
