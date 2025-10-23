import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { LoginDto } from '../../../employees/employee-dto/LoginDto';
import { UserDto } from '../../../employees/employee-dto/UserDto';
import { ErrorResponse } from '../../shared-dto/errorResponse';
import { AccountService } from '../../service/account-service';

import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';

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
export class Navbar {

  private fb = inject(NonNullableFormBuilder);

  protected accountService = inject(AccountService);

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
        // TODO add success message
      },
      error: () => {
        this.loginForm.patchValue({ password: '' });
      }
    });
  }

}
