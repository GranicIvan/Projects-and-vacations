import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { YearlyEarnings } from '../../monthly-log-dto/yearly-earnings';


@Component({
  selector: 'app-total-earnings',
  imports: [CommonModule, FormsModule],
  templateUrl: './total-earnings.html',
  styleUrl: './total-earnings.scss'
})
export class TotalEarnings  {
  protected monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);

  yearlyEarnings: YearlyEarnings[] = [];
  startYear: number;
  endYear: number;

  constructor() {
    const currentYear = new Date().getFullYear();
    this.startYear = currentYear - 5;
    this.endYear = currentYear;
  }



  loadYearlyEarnings() {
    if (!this.startYear || !this.endYear) {
      this.snackBar.open('Please enter both start and end year', 'Close', { duration: 3000 });
      return;
    }

    if (this.startYear > this.endYear) {
      this.snackBar.open('Start year must be before or equal to end year', 'Close', { duration: 3000 });
      return;
    }

    this.monthlyLogService.getTotalEarningsByYearRange(this.startYear, this.endYear).subscribe({
      next: (earnings) => {
        this.yearlyEarnings = earnings;

        console.log("Yearly Earnings:", this.yearlyEarnings);
        
        this.snackBar.open('Yearly earnings loaded successfully', 'Close', { duration: 3000 });
      },
      error: (err) => {
        this.snackBar.open(`Error loading yearly earnings: ${err.message}`, 'Close', {
          duration: 5000,
        });
      }
    });
  }

  getGrandTotalHours(): number {
    return this.yearlyEarnings.reduce((sum, earning) => sum + earning.hoursWorked, 0);
  }

  getGrandTotalEarnings(): number {
    return this.yearlyEarnings.reduce((sum, earning) => sum + earning.monthlyEarnings, 0);
  }
}
