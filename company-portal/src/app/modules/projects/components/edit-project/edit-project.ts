import { Component, inject, Input, OnInit } from '@angular/core';
import { FormControl, NonNullableFormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ProjectService } from '../../service/project-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

interface EditProjectForm {
  projectName: FormControl<string | undefined>;
  description: FormControl<string | undefined>;
}

@Component({
  selector: 'app-edit-project',
  imports: [ReactiveFormsModule],
  templateUrl: './edit-project.html',
  styleUrl: './edit-project.scss',
})
export class EditProject implements OnInit {
  private fb = inject(NonNullableFormBuilder);
  protected projectsService = inject(ProjectService);
  private snackBar = inject(MatSnackBar);
  private route = inject(ActivatedRoute);
  router = inject(Router);

  @Input() id!: number;

  readonly editProjectForm = this.fb.group<EditProjectForm>({
    projectName: this.fb.control(undefined),
    description: this.fb.control(undefined),
  });

  ngOnInit() {
 
  }

  

  updateProject() {
    if (this.editProjectForm.invalid) return;

    const formValue = this.editProjectForm.getRawValue();

    const projectData = {      
      projectName: formValue.projectName,
      description: formValue.description,
      id: this.id,
    };

    this.projectsService.updateProject(projectData).subscribe({
      next: (response) => {
        this.router.navigate(['/projects']);
        this.snackBar.open('Project updated successfully', 'Close', { duration: 3000 });
      },
      error: (error) => {
        this.snackBar.open('Failed to update project', 'Close', { duration: 3000 });
        console.error(error);
      },
    });
  }
}
