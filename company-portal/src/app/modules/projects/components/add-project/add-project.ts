import { Component, inject } from '@angular/core';
import {
  FormControl,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ProjectService } from '../../service/project-service';

interface AddProjectForm {
  projectName: FormControl<string>;
  description: FormControl<string>;
}

@Component({
  selector: 'app-add-project',
  imports: [ReactiveFormsModule],
  templateUrl: './add-project.html',
  styleUrl: './add-project.scss',
})
export class AddProject {
  private fb = inject(NonNullableFormBuilder);
  protected projectsService = inject(ProjectService);
  private snackBar = inject(MatSnackBar);
  router = inject(Router);

  readonly addProjectForm = this.fb.group<AddProjectForm>({
    projectName: this.fb.control('', {
      validators: [Validators.required, Validators.minLength(4)],
    }),
    description: this.fb.control(''),
  });

  createProject() {
    this.addProjectForm.markAllAsTouched();
    if (this.addProjectForm.invalid) return;

    const projectData = this.addProjectForm.value;
    this.projectsService
      .addProject({
        projectName: projectData.projectName!,
        description: projectData.description!,
      })
      .subscribe({
        next: () => {
          this.snackBar.open('Project created successfully', 'Close', { duration: 3000 });
          this.router.navigate(['/projects']);
        },
        error: () => {
          this.snackBar.open('Failed to create project', 'Close', { duration: 3000 });
        },
      });
  }

  protected isFieldInvalid(fieldName: keyof AddProjectForm): boolean {
    const field = this.addProjectForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  hasError(fieldName: keyof AddProjectForm, errorCode: string): boolean {
    const field = this.addProjectForm.get(fieldName);
    return !!(field && field.hasError(errorCode) && (field.dirty || field.touched));
  }

  protected getErrorMessage(fieldName: keyof AddProjectForm): string {
    const field = this.addProjectForm.get(fieldName);
    if (!field?.errors || !field?.touched) return '';

    if (field.errors['required']) {
      return 'This field is required';
    }
    if (field.errors['minlength']) {
      const requiredLength = field.errors['minlength'].requiredLength;
      return `Minimum length is ${requiredLength} characters`;
    }

    return 'Invalid field';
  }
}
