import { CanActivateFn, Router } from '@angular/router';
import { AccountService } from '../service/account-service';
import { inject } from '@angular/core';

export const adminGuard: CanActivateFn = (route, state) => {
  const accountService = inject(AccountService);
  const router = inject(Router);
  return accountService.isAdmin() ? true : router.parseUrl('/dashboard');
};
