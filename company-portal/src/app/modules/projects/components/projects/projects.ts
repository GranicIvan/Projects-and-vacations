import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ProjectService } from '../../service/project-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectShowDto } from '../../project-dto/ProjectShowDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-projects',
  imports: [CommonModule],
  templateUrl: './projects.html',
  styleUrls: ['./projects.scss'],
})
export class Projects {
  projectShowDtoList: ProjectShowDto[] = [];

  protected projectService = inject(ProjectService);

  router = inject(Router);
  private snackBar = inject(MatSnackBar);


  ngOnInit() {
    this.loadProjects();
  }

  loadProjects() {
    this.projectService.getAllProjects().subscribe((response) => {
      this.projectShowDtoList = response;
    });
  }

  addProject() {
    this.router.navigate(['/projects/new']);
    this.snackBar.open('Add Project clicked', 'Close', { duration: 5000 });
  }

  editProject(projectId: number) {
    this.router.navigate([`/projects/edit/${projectId}`]);
    this.snackBar.open(`Edit Project ${projectId} clicked`, 'Close', { duration: 5000 });
  }

  deleteProject(projectId: number) {
    this.projectService.deleteProject(projectId).subscribe(() => {
      this.loadProjects();
      this.snackBar.open(`Project ${projectId} deleted successfully`, 'Close', { duration: 5000 });
    });
  }
}
