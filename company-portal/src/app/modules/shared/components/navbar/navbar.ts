import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { LoginDto } from '../../../employees/employee-dto/LoginDto';
import { UserDto } from '../../../employees/employee-dto/UserDto';
import { ErrorResponse } from '../../shared-dto/errorResponse';

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

  readonly loginForm: LoginForm = this.fb.group({
    email: this.fb.control('', {
      validators: [Validators.required, Validators.email, Validators.minLength(4)],
    }),
    password: this.fb.control('', {
      validators: [Validators.required, Validators.minLength(4), Validators.maxLength(50)],
    }),
  });

  login() {
    // if (this.loginForm.invalid) return;

    // const loginCreds: LoginDto = this.loginForm.value;
    // this.accountService.login(loginCreds).subscribe({
    //   next: (user: UserDto) => {
    //     this.loginForm.reset();
    //     //.success(`Welcome back, ${user.firstName}.`);
    //   },
    //   error: (err) => {
    //     this.loginForm.patchValue({ password: '' });
    //     const errorResponse: ErrorResponse = err.error;
    //     // .error(errorResponse.message);
    //   }
    // });
  }

}
