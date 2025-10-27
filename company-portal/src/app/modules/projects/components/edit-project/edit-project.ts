import { Component, inject } from '@angular/core';
import { FormControl, NonNullableFormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ProjectService } from '../../service/project-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

type EditProjectForm = {
  id: FormControl<number | null>;
  projectName: FormControl<string>;
  description: FormControl<string>;
};

@Component({
  selector: 'app-edit-project',
  imports: [ReactiveFormsModule],
  templateUrl: './edit-project.html',
  styleUrl: './edit-project.scss',
})
export class EditProject {
  private fb = inject(NonNullableFormBuilder);
  protected projectsService = inject(ProjectService);
  private snackBar = inject(MatSnackBar);
  router = inject(Router);

  readonly editProjectForm = this.fb.group<EditProjectForm>({
    id: this.fb.control(null),
    projectName: this.fb.control(''),
    description: this.fb.control(''),
  });

  async updateProject() {
    if (this.editProjectForm.invalid) return;

    try {
      const formValue = this.editProjectForm.getRawValue();

      const id = this.router.url.split('/').pop();

      const projectData = {
        id: id ? parseInt(id, 10) : 0,
        projectName: formValue.projectName,
        description: formValue.description,
      };

      const response = await this.projectsService.updateProject(projectData);

      this.editProjectForm.reset();
      this.router.navigate(['/projects']);
      this.snackBar.open('Project updated successfully', 'Close', { duration: 3000 });
    } catch (error) {
      this.editProjectForm.reset();
      this.snackBar.open('Failed to update project', 'Close', { duration: 3000 });
    }
  }

}
