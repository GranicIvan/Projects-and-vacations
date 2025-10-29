import { Component, inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ProjectService } from '../../../projects/service/project-service';
import { MonthlyLogService } from '../../service/monthly-log-service';

type LogWorkedHoursForm = FormGroup<{
  projectId: FormControl<number>;
  hoursWorked: FormControl<number>;
  yearMonth: FormControl<string>;
}>;

@Component({
  selector: 'app-log-worked-hours',
  imports: [ReactiveFormsModule],
  templateUrl: './log-worked-hours.html',
  styleUrl: './log-worked-hours.scss'
})
export class LogWorkedHours implements OnInit {
  private fb = inject(NonNullableFormBuilder);
  protected projectsService = inject(ProjectService);
  protected monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);
  private router = inject(Router);

  projectShowDtoList: any[] = [];

  @Input() id!: number;

  readonly logWorkedHoursForm: LogWorkedHoursForm = this.fb.group({
    projectId: this.fb.control(0, {
      validators: [Validators.required, Validators.min(1)],
    }),
    hoursWorked: this.fb.control(0, {
      validators: [Validators.required, Validators.min(1), Validators.max(744)],
    }),
    yearMonth: this.fb.control('', {
      validators: [Validators.required],
    }),
  }) as LogWorkedHoursForm;

  ngOnInit() {
    // Set default to current month
    const now = new Date();
    const currentMonth = `${now.getFullYear()}-${String(now.getMonth() + 1).padStart(2, '0')}`;
    this.logWorkedHoursForm.patchValue({ yearMonth: currentMonth });

    // Load projects
    this.projectsService.getWithUser(this.id).subscribe({
      next: (projects) => {
        this.projectShowDtoList = projects;
      },
      error: (err) => {
        this.snackBar.open(`Error loading projects: ${err.message}`, 'Close', {
          duration: 5000,
        });
      },
    });
  }

  logHours() {
    this.logWorkedHoursForm.markAllAsTouched();
    if (this.logWorkedHoursForm.invalid) return;


    
  const assignmentId = 0;

    const { projectId, hoursWorked, yearMonth } = this.logWorkedHoursForm.getRawValue();

    this.monthlyLogService.logWorkedHours(projectId, hoursWorked, yearMonth).subscribe({
      next: () => {
        this.snackBar.open('Hours logged successfully', 'Close', {
          duration: 3000,
        });
        this.router.navigate(['/monthly-log/my-logged-hours/', this.id]);
      },
      error: (err) => {
        this.snackBar.open(`Error: ${err.message}`, 'Close', {
          duration: 5000,
        });
      },
    });
  }

  protected isFieldInvalid(fieldName: keyof LogWorkedHoursForm['controls']): boolean {
    const field = this.logWorkedHoursForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  protected hasError(fieldName: keyof LogWorkedHoursForm['controls'], errorType: string): boolean {
    return !!this.logWorkedHoursForm.get(fieldName)?.hasError(errorType);
  }

  protected getErrorMessage(fieldName: keyof LogWorkedHoursForm['controls']): string {
    const field = this.logWorkedHoursForm.get(fieldName);
    if (!field?.errors || !field?.touched) return '';

    const errors: Record<string, Record<string, string>> = {
      projectId: {
        required: 'Project is required',
        min: 'Please select a project',
      },
      hoursWorked: {
        required: 'Hours worked is required',
        min: 'Hours must be at least 1',
        max: 'Hours cannot exceed 744 (31 days × 24 hours)',
      },
      yearMonth: {
        required: 'Year-Month is required',
      },
    };

    const fieldErrors = errors[fieldName as string];
    const errorKey = Object.keys(field.errors)[0];
    return fieldErrors?.[errorKey] || '';
  }
}
