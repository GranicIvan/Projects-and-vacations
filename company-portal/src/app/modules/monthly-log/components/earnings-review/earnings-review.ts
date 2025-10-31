import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MonthlyLogDto } from '../../monthly-log-dto/monthly-log-dto';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectShowDto } from '../../../projects/project-dto/ProjectShowDto';
import { ProjectService } from '../../../projects/service/project-service';

@Component({
  selector: 'app-earnings-review',
  imports: [CommonModule, FormsModule],
  templateUrl: './earnings-review.html',
  styleUrl: './earnings-review.scss'
})
export class EarningsReview implements OnInit {
 protected monthlyLogService = inject(MonthlyLogService);
  protected projectService = inject(ProjectService);
  private snackBar = inject(MatSnackBar);

  monthlyLogDtoList: MonthlyLogDto[] = [];
  filteredLogs: MonthlyLogDto[] = [];
  projects: ProjectShowDto[] = [];
  selectedProjectId: number = 0;

  ngOnInit() {
    this.loadProjects();
  }

  loadProjects() {
    this.projectService.getAllProjects().subscribe({
      next: (projects) => {
        this.projects = projects;
      },
      error: (err) => {
        this.snackBar.open(`Error loading projects: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

  getAllLoggedHours() {
    this.monthlyLogService.getAllLoggedHours().subscribe({
      next: (logs) => {
        this.monthlyLogDtoList = logs;
        this.filteredLogs = logs;
      },
      error: (err) => {
        this.snackBar.open(`Error loading logged hours: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

  filterByProject() {
    if (this.selectedProjectId == 0) {
      this.getAllLoggedHours();
    } else {
      this.monthlyLogService.getLogsByProject(this.selectedProjectId).subscribe({
        next: (logs) => {
          this.filteredLogs = logs;
          this.calculateTotals();
        },
        error: (err) => {
          this.snackBar.open(`Error loading project logs: ${err.message}`, 'Close', {
            duration: 5000,
          });
        }
      });
    }
  }

  calculateTotals() {
    // Calculate total hours and earnings for display
    const totalHours = this.filteredLogs.reduce((sum, log) => sum + log.hoursWorked, 0);
    const totalEarnings = this.filteredLogs.reduce(
      (sum, log) => sum + (log.hoursWorked * log.projectAssignment.hourlyPay), 
      0
    );
    
    return { totalHours, totalEarnings };
  }

  getTotalHours(): number {
    return this.filteredLogs.reduce((sum, log) => sum + log.hoursWorked, 0);
  }

  getTotalEarnings(): number {
    return this.filteredLogs.reduce(
      (sum, log) => sum + (log.hoursWorked * log.projectAssignment.hourlyPay),
      0
    );
  }
}
