import { Component, inject, Input } from '@angular/core';
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
import { Router } from '@angular/router';

type EditEmployeeForm = FormGroup<{
  id: FormControl<number | null>;
  firstName: FormControl<string | null>;
  lastName: FormControl<string | null>;
  dateOfBirth: FormControl<Date | null>;
  email: FormControl<string | null>;
  password: FormControl<string | null>;
  address: FormControl<string | null>;
  vacationDaysLeft: FormControl<number | null>;
}>;

@Component({
  selector: 'app-edit-employee',
  imports: [ReactiveFormsModule],
  templateUrl: './edit-employee.html',
  styleUrl: './edit-employee.scss',
})
export class EditEmployee {
  private fb = inject(NonNullableFormBuilder);
  protected employeeService = inject(EmployeeService);
  private snackBar = inject(MatSnackBar);
  router = inject(Router);

  @Input() id!: number;

  readonly newEmployeeForm: EditEmployeeForm = this.fb.group({
    id: this.fb.control(null),
    firstName: this.fb.control(null, {
      validators: [Validators.minLength(2)],
    }),
    lastName: this.fb.control(null, {
      validators: [Validators.minLength(2)],
    }),
    dateOfBirth: this.fb.control(null),
    email: this.fb.control(null, {
      validators: [Validators.email],
    }),
    password: this.fb.control(null, {
      validators: [Validators.minLength(4)],
    }),
    address: this.fb.control(null),
    vacationDaysLeft: this.fb.control(null, {
      validators: [Validators.min(0)],
    })
  }) as EditEmployeeForm;

  async updateEmployee() {
    if (this.newEmployeeForm.invalid) return;

    try {
      const formValue = this.newEmployeeForm.getRawValue();
      const employeeData = Object.fromEntries(
        Object.entries(formValue).map(([key, value]) => [key, value === null ? undefined : value]),
      );

      // const id = this.router.url.split('/').pop();

      console.log('ID je:', this.id);
      employeeData['id'] = this.id;

      for (const [key, value] of Object.entries(employeeData)) {
        if (value !== undefined && value.toString().trim() === '') {
          delete employeeData[key];
        }
        if (value === undefined) {
          delete employeeData[key];
        }
      }

      const response = await this.employeeService.updateEmployee(employeeData);
      this.newEmployeeForm.reset();
      this.router.navigate(['/employees']);
      this.snackBar.open('User updated successfully', 'Close', { duration: 5000 });
    } catch (error) {
      this.snackBar.open('Failed to update user', 'Close', { duration: 5000 });
      console.error(error);
    }
  }


  protected isFieldInvalid(fieldName: keyof EditEmployeeForm['controls']): boolean {
    const field = this.newEmployeeForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  protected hasError(fieldName: keyof EditEmployeeForm['controls'], errorType: string): boolean {
    return !!this.newEmployeeForm.get(fieldName)?.hasError(errorType);
  }

  protected getErrorMessage(fieldName: keyof EditEmployeeForm['controls']): string {
    const field = this.newEmployeeForm.get(fieldName);
    if (!field?.errors || !field?.touched) return '';


    if (field.errors['minlength']) {
      const requiredLength = field.errors['minlength'].requiredLength;
      return `Minimum length is ${requiredLength} characters`;
    }
    if (field.errors['email']) {
      return 'Please enter a valid email address';
    }
    if (field.errors['min']) {
      const min = field.errors['min'].min;
      return `Value must be at least ${min}`;
    }

    return 'Invalid field';
  }
}
