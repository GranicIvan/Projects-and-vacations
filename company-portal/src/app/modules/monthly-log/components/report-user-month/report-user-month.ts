import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MonthlyLogDto } from '../../monthly-log-dto/monthly-log-dto';

@Component({
  selector: 'app-report-user-month',
  imports: [CommonModule, FormsModule],
  templateUrl: './report-user-month.html',
  styleUrl: './report-user-month.scss'
})
export class ReportUserMonth {
  private monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);

  userId: number | null = null;
  selectedYearMonth: string = '';
  reportData: MonthlyLogDto[] | null = null;
  loading: boolean = false;

  loadReport() {
    if (!this.userId || !this.selectedYearMonth) {
      this.snackBar.open('Please enter both User ID and Year-Month', 'Close', { duration: 3000 });
      return;
    }

    this.loading = true;
    this.reportData = null;

    this.monthlyLogService.getReportForUserMonth(this.userId, this.selectedYearMonth).subscribe({
      next: (data) => {
        this.reportData = data;
        this.loading = false;
      },
      error: (err) => {
        this.snackBar.open(`Error loading report: ${err.message}`, 'Close', { duration: 5000 });
        this.loading = false;
      }
    });
  }

  getTotalEarnings(): number {
    if (!this.reportData || this.reportData.length === 0) return 0;
    return this.reportData.reduce((sum, log) => {
      return sum + (log.projectAssignment.hourlyPay * log.hoursWorked);
    }, 0);
  }

  getTotalHours(): number {
    if (!this.reportData || this.reportData.length === 0) return 0;
    return this.reportData.reduce((sum, log) => sum + log.hoursWorked, 0);
  }

  getUserInfo() {
    if (!this.reportData || this.reportData.length === 0) return null;
    return this.reportData[0].projectAssignment.user;
  }
}
