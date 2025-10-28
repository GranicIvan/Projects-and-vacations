import { Component, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserRole } from '../../../shared/shared-dto/UserRole';
import { EmployeeService } from '../../service/employee-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CreateUserDto } from '../../employee-dto/CreateUserDto';

type NewEmployeeForm = FormGroup<{
  firstName: FormControl<string | null>;
  lastName: FormControl<string | null>;
  dateOfBirth: FormControl<Date | null>;
  email: FormControl<string | null>;
  password: FormControl<string | null>;
  address: FormControl<string | null>;
  vacationDaysLeft: FormControl<number | null>;
  userRole: FormControl<UserRole | null>;
}>;

@Component({
  selector: 'app-new-employee',
  imports: [ReactiveFormsModule],
  templateUrl: './new-employee.html',
  styleUrl: './new-employee.scss',
})
export class NewEmployee {
  private fb = inject(NonNullableFormBuilder);
  protected employeeService = inject(EmployeeService);
  private snackBar = inject(MatSnackBar);
  router = inject(Router);

  readonly newEmployeeForm: NewEmployeeForm = this.fb.group({
    firstName: this.fb.control(null, {
      validators: [Validators.required, Validators.minLength(2)],
    }),
    lastName: this.fb.control(null, {
      validators: [Validators.required, Validators.minLength(2)],
    }),
    dateOfBirth: this.fb.control(null),
    email: this.fb.control(null, {
      validators: [Validators.required, Validators.email],
    }),
    password: this.fb.control(null, {
      validators: [Validators.required, Validators.minLength(4)],
    }),
    address: this.fb.control(null),
    vacationDaysLeft: this.fb.control(null, {
      validators: [Validators.min(0)],
    }),
    userRole: this.fb.control('EMPLOYEE'),
  }) as NewEmployeeForm;

  async createEmployee() {
    this.newEmployeeForm.markAllAsTouched();
    if (this.newEmployeeForm.invalid) return;
    

    const formValue = this.newEmployeeForm.getRawValue();
    const employee: Partial<CreateUserDto> = Object.entries(formValue).reduce(
      (acc, [key, value]) => {
        if (value !== null) {
          acc[key as keyof CreateUserDto] = value as any;
        }
        return acc;
      },
      {} as Partial<CreateUserDto>,
    );

    this.employeeService.createEmployee(employee).subscribe({
      next: (response) => {
        this.newEmployeeForm.reset();
        this.router.navigate(['/employees']);
        this.snackBar.open('Employee created successfully', 'Close', { duration: 5000 });
      },
      error: (error) => {
        this.snackBar.open('Failed to create employee', 'Close', { duration: 5000 });
        console.error(error);
      },
    });
  }
}
