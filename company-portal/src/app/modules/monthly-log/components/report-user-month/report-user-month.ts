import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule, NonNullableFormBuilder } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MonthlyLogDto } from '../../monthly-log-dto/monthly-log-dto';
import { UserDto } from '../../../employees/employee-dto/UserDto';
import { EmployeeService } from '../../../employees/service/employee-service';
import { Console } from 'console';
import { CreateUserDto } from '../../../employees/employee-dto/CreateUserDto';


type ReportUserMonthParams = {
  userId: FormControl<number| null>;
  yearMonth: FormControl<string | null>;
};

@Component({
  selector: 'app-report-user-month',
  imports: [CommonModule, FormsModule],
  templateUrl: './report-user-month.html',
  styleUrl: './report-user-month.scss'
})
export class ReportUserMonth implements OnInit {
  private fb = inject(NonNullableFormBuilder);
  private monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);
  protected employeeService = inject(EmployeeService);

  userId: number | null = null;
  selectedYearMonth: string = '';
  reportData: MonthlyLogDto[] | null = null;
  loading: boolean = false;
  userShowDtoList: CreateUserDto[] = [];

  readonly reportForm: FormGroup<ReportUserMonthParams> = this.fb.group({
    userId: new FormControl<number | null>(null),
    yearMonth: new FormControl<string | null>(null),
  });

  ngOnInit() {
    this.loadUserList();
    // Don't log here - the data isn't loaded yet
  }

  loadReport() {
    if (!this.userId || !this.selectedYearMonth || this.userId <= 0) {
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


  loadUserList() {
    this.employeeService.getUsers().subscribe({
      next: (users) => {
        this.userShowDtoList = users;
        console.log("Loaded user list:", this.userShowDtoList);  // Log here instead
      },
      error: (err) => {
        this.snackBar.open(`Error: ${err.message}`, 'Close', {
          duration: 5000,
        });
      },
    });
  }
}
