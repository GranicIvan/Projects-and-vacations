import { Component, inject } from '@angular/core';
import { FormControl, NonNullableFormBuilder, ReactiveFormsModule } from '@angular/forms';
import { EmployeeService } from '../../../employees/service/employee-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ProjectService } from '../../service/project-service';

type AddProjectForm = {
  projectName: FormControl<string>;
  description: FormControl<string>;
};

@Component({
  selector: 'app-add-project',
  imports: [ReactiveFormsModule],
  templateUrl: './add-project.html',
  styleUrl: './add-project.scss'
})
export class AddProject {
  private fb = inject(NonNullableFormBuilder);
  protected projectsService = inject(ProjectService);
  private snackBar = inject(MatSnackBar);
  router = inject(Router);
  
  readonly addProjectForm = this.fb.group<AddProjectForm>({
    projectName: this.fb.control(''),
    description: this.fb.control(''),
  });

  createProject() {
    if (this.addProjectForm.valid) {
      const projectData = this.addProjectForm.value;
      this.projectsService.addProject({ projectName: projectData.projectName!, description: projectData.description! }).subscribe({
        next: () => {
          this.snackBar.open('Project created successfully', 'Close', { duration: 3000 });
          this.router.navigate(['/projects']);
        },
        error: () => {
          this.snackBar.open('Failed to create project', 'Close', { duration: 3000 });
        }
      });
    }
  }
}
