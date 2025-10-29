import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MonthlyLogDto } from '../../monthly-log-dto/monthly-log-dto';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectShowDto } from '../../../projects/project-dto/ProjectShowDto';


@Component({
  selector: 'app-my-logged-hours',
  imports: [CommonModule, FormsModule],
  templateUrl: './my-logged-hours.html',
  styleUrl: './my-logged-hours.scss'
})
export class MyLoggedHours implements OnInit {
  protected monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);

  monthlyLogDtoList: MonthlyLogDto[] = [];
  filteredLogs: MonthlyLogDto[] = []; 
  projects: ProjectShowDto[] = [];
  selectedProjectId: number = 0; // Change from number | null to just number


  ngOnInit() {
    this.getMyLoggedHours();
  }

  getMyLoggedHours() {
    this.monthlyLogService.getMyLoggedHours().subscribe({
      next: (logs) => {
        this.monthlyLogDtoList = logs;
        this.filteredLogs = logs; 
        this.loadProjects();
        this.snackBar.open('Logged hours loaded successfully', 'Close', { duration: 3000 });
      },
      error: (err) => {
        this.snackBar.open(`Error loading logged hours: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

  deleteLog(logId: number) {
    this.monthlyLogService.deleteLog(logId).subscribe({
      next: () => {
        this.snackBar.open('Log deleted successfully', 'Close', { duration: 3000 });
        this.getMyLoggedHours();
      },
      error: (err) => {
        this.snackBar.open(`Error deleting log: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

  filterByProject() {
    if (this.selectedProjectId > 0) { 
      this.filteredLogs = this.monthlyLogDtoList.filter(log =>
        log.projectAssignment.project.id == this.selectedProjectId
      );
    } else {
      this.filteredLogs = this.monthlyLogDtoList;
    }
  }

  loadProjects() {

    this.projects = Array.from(
      new Map(
      this.monthlyLogDtoList.map(log => [log.projectAssignment.project.id, log.projectAssignment.project])
      ).values()
    );

    this.projects.sort((a, b) => a.projectName.localeCompare(b.projectName));

  }
}
