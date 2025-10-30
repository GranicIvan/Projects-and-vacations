import { Component, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  ReactiveFormsModule,
} from '@angular/forms';
import { ProjectService } from '../../service/project-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserDto } from '../../../employees/employee-dto/UserDto';
import { EmployeeService } from '../../../employees/service/employee-service';

type AddEmployeeToProjectForm = FormGroup<{
  userId: FormControl<number>;
  projectId: FormControl<number>;
  hourlyRate: FormControl<number>;
}>;

@Component({
  selector: 'app-add-employee-to-project',
  imports: [ReactiveFormsModule],
  templateUrl: './add-employee-to-project.html',
  styleUrl: './add-employee-to-project.scss',
})
export class AddEmployeeToProject {
  private fb = inject(NonNullableFormBuilder);
  protected projectsService = inject(ProjectService);
  private snackBar = inject(MatSnackBar);
  private router = inject(Router);

  protected userService = inject(EmployeeService);

  userShowDtoList: UserDto[] = [];
  projectShowDtoList: any[] = [];

  readonly addEmployeeToProjectForm: AddEmployeeToProjectForm = this.fb.group({
    userId: this.fb.control(0),
    projectId: this.fb.control(0),
    hourlyRate: this.fb.control(0),
  }) as AddEmployeeToProjectForm;

  ngOnInit() {
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.userShowDtoList = users;
      },
      error: (err) => {
        this.snackBar.open(`Error: ${err.message}`, 'Close', {
          duration: 5000,
        });
      },
    });

    this.projectsService.getAllProjects().subscribe({
      next: (projects) => {
        this.projectShowDtoList = projects;
      },
      error: (err) => {
        this.snackBar.open(`Error: ${err.message}`, 'Close', {
          duration: 5000,
        });
      },
    });
  }

  addEmployee() {
    this.addEmployeeToProjectForm.markAllAsTouched();
    if (this.addEmployeeToProjectForm.invalid) return;

    const { userId, projectId, hourlyRate } = this.addEmployeeToProjectForm.getRawValue();

    console.log('userId:', userId, ', projectId:', projectId, ', hourlyRate:', hourlyRate);

    this.projectsService.addEmployeeToProject(userId, projectId, hourlyRate).subscribe({
      next: () => {
        this.snackBar.open('Employee added to project successfully', 'Close', {
          duration: 3000,
        });
        this.router.navigate(['/projects']);
      },
      error: (err) => {
        this.snackBar.open(`Error: ${err.message}`, 'Close', {
          duration: 5000,
        });
      },
    });
  }

  protected isFieldInvalid(fieldName: keyof AddEmployeeToProjectForm['controls']): boolean {
    const field = this.addEmployeeToProjectForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  protected hasError(
    fieldName: keyof AddEmployeeToProjectForm['controls'],
    errorType: string,
  ): boolean {
    return !!this.addEmployeeToProjectForm.get(fieldName)?.hasError(errorType);
  }
  protected getErrorMessage(fieldName: keyof AddEmployeeToProjectForm['controls']): string {
    const field = this.addEmployeeToProjectForm.get(fieldName);
    if (!field?.errors || !field?.touched) return '';

    if (field.errors['required']) {
      return 'This field is required';
    }
    if (field.errors['min']) {
      return 'Value must be greater than or equal to ' + field.errors['min'].min;
    }
    return 'Invalid field';
  }
}
