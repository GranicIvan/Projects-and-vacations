import { CanActivateFn, Router } from '@angular/router';
import { AccountService } from '../service/account-service';
import { inject } from '@angular/core';
import { map, catchError, of } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const accountService = inject(AccountService);
  const router = inject(Router);

  // If user is already loaded, allow immediately
  if (accountService.currentUser()) {
    return true;
  }

  // Otherwise, try to load the user from the server
  return accountService.loadCurrentUser().pipe(
    map((user) => {
      accountService.currentUser.set(user);
      return true;
    }),
    catchError(() => {
      console.log('User not authenticated, redirecting to dashboard');
      router.navigate(['/dashboard']);
      return of(false);
    })
  );
};
