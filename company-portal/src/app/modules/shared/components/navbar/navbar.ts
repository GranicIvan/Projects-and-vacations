import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { LoginDto } from '../../../employees/employee-dto/LoginDto';
import { UserDto } from '../../../employees/employee-dto/UserDto';
import { ErrorResponse } from '../../shared-dto/errorResponse';
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
  styleUrl: './navbar.scss'
})
export class Navbar implements OnInit {

  private fb = inject(NonNullableFormBuilder);

  protected accountService = inject(AccountService);

  private snackBar = inject(MatSnackBar);

  readonly loginForm: LoginForm = this.fb.group({
    email: this.fb.control('', {
      validators: [Validators.required, Validators.email, Validators.minLength(4)],
    }),
    password: this.fb.control('', {
      validators: [Validators.required, Validators.minLength(4)],
    }),
  });

  ngOnInit() {
    if (!this.accountService.currentUser()) {
      this.accountService.getCurrentUser().subscribe({
        next: (user) => {
          this.accountService.currentUser.set(user);
        },
        error: () => {
        }
      });
    }
  }

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
        this.snackBar.open('Login failed. Please check your credentials.', 'Close', { duration: 5000 });
      }
    });
  }

}
