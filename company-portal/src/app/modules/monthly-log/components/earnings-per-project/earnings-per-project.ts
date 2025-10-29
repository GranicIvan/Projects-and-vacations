import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MonthlyLogDto } from '../../monthly-log-dto/monthly-log-dto';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MonthlyEarningsByProject } from '../../monthly-log-dto/monthly-earnings-by-project';


@Component({
  selector: 'app-earnings-per-project',
  imports: [CommonModule, FormsModule],
  templateUrl: './earnings-per-project.html',
  styleUrl: './earnings-per-project.scss'
})
export class EarningsPerProject implements OnInit {
  protected monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);

  monthlyLogDto: MonthlyLogDto[] = [];

  // monthlyLogDtoList: MonthlyEarningsByProject[] = [];
  projectEarnings: MonthlyEarningsByProject[] = [];
  selectedYearMonth: string = '';
  yearOption: number = -1;

  ngOnInit() {
    // Set default to current month
    const now = new Date();
    this.selectedYearMonth = `${now.getFullYear()}-${String(now.getMonth() + 1).padStart(2, '0')}`;
    this.yearOption = now.getFullYear();
  }

  filterByYearMonth() {
    if (!this.selectedYearMonth) {
      this.snackBar.open('Please select a year-month', 'Close', { duration: 3000 });
      return;
    }

    this.monthlyLogService.getLogsByYearMonthGroupedByProject(this.selectedYearMonth).subscribe({
      next: (logs) => {
        this.projectEarnings = logs;
      },
      error: (err) => {
        this.snackBar.open(`Error loading logged hours: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

    filterByYear() {
    if (!this.selectedYearMonth) {
      this.snackBar.open('Please select a year-month', 'Close', { duration: 3000 });
      return;
    }

    this.monthlyLogService.getLogsByYearGroupedByProject(this.yearOption).subscribe({
      next: (logs) => {
        this.projectEarnings = logs;
      },
      error: (err) => {
        this.snackBar.open(`Error loading logged hours: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }




  getGrandTotalHours(): number {
    return this.projectEarnings.reduce((sum, earning) => sum + earning.hoursWorked, 0);
  }

  getGrandTotalEarnings(): number {
    return this.projectEarnings.reduce((sum, earning) => sum + earning.monthlyEarnings, 0);
  }
}
