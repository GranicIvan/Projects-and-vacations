import { CanActivateFn } from '@angular/router';
import { AccountService } from '../service/account-service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const accountService = inject(AccountService);


  // If user is already loaded, allow immediately
  if (accountService.currentUser()) {
    return true;
  }

  return false;
};
