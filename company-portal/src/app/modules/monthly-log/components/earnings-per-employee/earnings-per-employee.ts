import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MonthlyLogDto } from '../../monthly-log-dto/monthly-log-dto';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EarningsByEmployee } from '../../monthly-log-dto/earnings-by-employee';

@Component({
  selector: 'app-earnings-per-employee',
  imports: [CommonModule, FormsModule],
  templateUrl: './earnings-per-employee.html',
  styleUrl: './earnings-per-employee.scss'
})
export class EarningsPerEmployee implements OnInit {
  protected monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);

  monthlyLogDto: MonthlyLogDto[] = [];
  employeeEarnings: EarningsByEmployee[] = [];
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

    this.monthlyLogService.getLogsByYearMonthGroupedByEmployee(this.selectedYearMonth).subscribe({
      next: (logs) => {
        this.employeeEarnings = logs;
      },
      error: (err) => {
        this.snackBar.open(`Error loading logged hours: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

  filterByYear() {
    if (!this.yearOption) {
      this.snackBar.open('Please enter a year', 'Close', { duration: 3000 });
      return;
    }

    this.monthlyLogService.getLogsByYearGroupedByEmployee(this.yearOption).subscribe({
      next: (logs) => {
        this.employeeEarnings = logs;
      },
      error: (err) => {
        this.snackBar.open(`Error loading logged hours: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

  getGrandTotalHours(): number {
    return this.employeeEarnings.reduce((sum, earning) => sum + earning.hoursWorked, 0);
  }

  getGrandTotalEarnings(): number {
    return this.employeeEarnings.reduce((sum, earning) => sum + earning.monthlyEarnings, 0);
  }
}
