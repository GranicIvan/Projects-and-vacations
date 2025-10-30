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
  id: FormControl<number | undefined>;
  firstName: FormControl<string | undefined>;
  lastName: FormControl<string | undefined>;
  dateOfBirth: FormControl<Date | undefined>;
  email: FormControl<string | undefined>;
  password: FormControl<string | undefined>;
  address: FormControl<string | undefined>;
  vacationDaysLeft: FormControl<number | undefined>;
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
    id: this.fb.control<number | undefined>(undefined),
    firstName: this.fb.control<string | undefined>(undefined, {
      validators: [Validators.minLength(2)],
    }),
    lastName: this.fb.control<string | undefined>(undefined, {
      validators: [Validators.minLength(2)],
    }),
    dateOfBirth: this.fb.control<Date | undefined>(undefined),
    email: this.fb.control<string | undefined>(undefined, {
      validators: [Validators.email],
    }),
    password: this.fb.control<string | undefined>(undefined, {
      validators: [Validators.minLength(4)],
    }),
    address: this.fb.control<string | undefined>(undefined),
    vacationDaysLeft: this.fb.control<number | undefined>(undefined, {
      validators: [Validators.min(0)],
    })
  }) as EditEmployeeForm;

  async updateEmployee() {
    if (this.newEmployeeForm.invalid) return;

    try {
      const formValue = this.newEmployeeForm.getRawValue();
      console.log('Form value is:', formValue);
      const employeeData = Object.fromEntries(
        Object.entries(formValue).map(([key, value]) => [key, value === null ? undefined : value]),
      );

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
