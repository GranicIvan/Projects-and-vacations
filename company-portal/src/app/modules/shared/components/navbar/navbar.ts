import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { LoginDto } from '../../../employees/employee-dto/LoginDto';
import { UserDto } from '../../../employees/employee-dto/UserDto';
import { ErrorResponse } from '../../shared-dto/errorResponse';
import { AccountService } from '../../service/account-service';

type LoginForm = FormGroup<{
  email: FormControl<string>;
  password: FormControl<string>;
}>;

@Component({
  selector: 'app-navbar',
  imports: [ReactiveFormsModule, RouterLink],
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
        //.success(`Welcome back, ${user.firstName}.`);
      },
      error: () => {
        this.loginForm.patchValue({ password: '' });
        // .error(errorResponse.message);
      }
    });
  }

}
