import { Component, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { LoginDto } from '../../../employees/employee-dto/LoginDto';
import { AccountService } from '../../service/account-service';

import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { MatSnackBar } from '@angular/material/snack-bar';

type LoginForm = FormGroup<{
  email: FormControl<string>;
  password: FormControl<string>;
}>;

@Component({
  selector: 'app-navbar',
  imports: [ReactiveFormsModule, RouterLink, NgbDropdownModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {
  private fb = inject(NonNullableFormBuilder);
  protected accountService = inject(AccountService);
  private snackBar = inject(MatSnackBar);
  router = inject(Router);

  readonly loginForm: LoginForm = this.fb.group({
    email: this.fb.control('', {
      validators: [Validators.required, Validators.email, Validators.minLength(4)],
    }),
    password: this.fb.control('', {
      validators: [Validators.required, Validators.minLength(4)],
    }),
  });

  login() {
    if (this.loginForm.invalid) return;

    const loginCreds = this.loginForm.getRawValue() as LoginDto;

    this.accountService.login(loginCreds).subscribe({
      next: () => {
        this.loginForm.reset();
        this.snackBar.open('Login successful', 'Close', { duration: 5000 });
      },
      error: () => {
        this.loginForm.patchValue({ password: '' });
        this.snackBar.open('Login failed. Please check your credentials.', 'Close', {
          duration: 5000,
        });
      },
    });
    this.router.navigate(['/dashboard']);
  }

  isLoggedIn() {
    return this.accountService.isLoggedIn();
  }

  logout() {
    this.accountService.logout();
    this.router.navigate(['/dashboard']);
  }

  isAdmin() {
    return this.accountService.isAdmin();
  }

  goToLogWorkHours() {
    this.router.navigate(['/monthly-log/log-work-hours/' + this.accountService.currentUser()?.id]);
  }

  goToMyLoggedHours() {
    this.router.navigate(['/monthly-log/my-logged-hours/' + this.accountService.currentUser()?.id]);
  }
}
