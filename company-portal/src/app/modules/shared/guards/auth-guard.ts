import { CanActivateFn, Router } from '@angular/router';
import { AccountService } from '../service/account-service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
    const accountService = inject(AccountService);
    const router = inject(Router);
  if (accountService.currentUser()) return true;

  console.log('User not authenticated, redirecting to dashboard');
  router.navigate(['/dashboard']);
  return false;
};
