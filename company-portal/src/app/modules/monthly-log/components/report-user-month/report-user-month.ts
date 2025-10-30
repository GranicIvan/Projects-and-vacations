import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, NonNullableFormBuilder } from '@angular/forms';
import { MonthlyLogService } from '../../service/monthly-log-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MonthlyLogDto } from '../../monthly-log-dto/monthly-log-dto';
import { EmployeeService } from '../../../employees/service/employee-service';
import { CreateUserDto } from '../../../employees/employee-dto/CreateUserDto';

type ReportUserMonthForm = FormGroup<{
  userId: FormControl<number>;
  yearMonth: FormControl<string>;
}>;

@Component({
  selector: 'app-report-user-month',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './report-user-month.html',
  styleUrl: './report-user-month.scss'
})
export class ReportUserMonth implements OnInit {
  private fb = inject(NonNullableFormBuilder);
  private monthlyLogService = inject(MonthlyLogService);
  private snackBar = inject(MatSnackBar);
  protected employeeService = inject(EmployeeService);

  reportData: MonthlyLogDto[] | null = null;
  loading: boolean = false;
  userShowDtoList: CreateUserDto[] = [];

  readonly reportForm: ReportUserMonthForm = this.fb.group({
    userId: this.fb.control(0),
    yearMonth: this.fb.control(''),
  }) as ReportUserMonthForm;

  ngOnInit() {
    this.loadUserList();
  }

  loadReport() {
    const { userId, yearMonth } = this.reportForm.getRawValue();

    if (!userId || !yearMonth || userId <= 0) {
      this.snackBar.open('Please select both User and Year-Month', 'Close', { duration: 3000 });
      return;
    }

    this.loading = true;
    this.reportData = null;

    this.monthlyLogService.getReportForUserMonth(userId, yearMonth).subscribe({
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
        console.log("Loaded user list:", this.userShowDtoList);
      },
      error: (err) => {
        this.snackBar.open(`Error: ${err.message}`, 'Close', {
          duration: 5000,
        });
      },
    });
  }
}
