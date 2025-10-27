import { Component, inject } from '@angular/core';
import { Form, FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule } from '@angular/forms';
import { UserRole } from '../../../shared/shared-dto/UserRole';
import { EmployeeService } from '../../service/employee-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

type NewEmployeeForm = FormGroup<{
  id: FormControl<number | null>;
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
    id: this.fb.control(null),
    firstName: this.fb.control(null),
    lastName: this.fb.control(null),
    dateOfBirth: this.fb.control(null),
    email: this.fb.control(null),
    password: this.fb.control(null),
    address: this.fb.control(null),
    vacationDaysLeft: this.fb.control(null),
    userRole: this.fb.control('Select role...'),
  }) as NewEmployeeForm;

  async createEmployee() {
    if (this.newEmployeeForm.invalid) return;

    console.log(this.newEmployeeForm.getRawValue());

    try {
      const formValue = this.newEmployeeForm.getRawValue();
      const employeeData = Object.fromEntries(
        Object.entries(formValue).map(([key, value]) => [key, value === null ? undefined : value]),
      );
      const response = await this.employeeService.createEmployee(employeeData);
      this.newEmployeeForm.reset();
      this.router.navigate(['/employees']);
      this.snackBar.open('User created successfully', 'Close', { duration: 5000 });

    } catch (error) {
      this.snackBar.open('Failed to create user', 'Close', { duration: 5000 });
      console.error(error);
    }
  }
}
