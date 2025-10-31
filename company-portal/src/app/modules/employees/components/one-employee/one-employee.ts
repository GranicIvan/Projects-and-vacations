import { Component, inject, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../service/employee-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { AccountService } from '../../../shared/service/account-service';
import { DetailedUserDto } from '../../employee-dto/DetailedUserDto';

@Component({
  selector: 'app-one-employee',
  imports: [CommonModule],
  templateUrl: './one-employee.html',
  styleUrl: './one-employee.scss',
})
export class OneEmployee implements OnInit {
  protected employeeService = inject(EmployeeService);
  protected accountService = inject(AccountService);
  private snackBar = inject(MatSnackBar);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  id = -1;
  @Input() input: string | undefined;

  employee: DetailedUserDto | null = null;


  ngOnInit() {
    // Method 1: Get the full URL and split it
    const urlSegments = this.router.url.split('/');
    const lastSegment = urlSegments[urlSegments.length - 1];


    if (lastSegment === 'me') {
      // Load current user's data

      this.accountService.loadCurrentUserDetailed().subscribe({
        next: (raw) => {
          
          console.log(raw);
          // console.log(this.employee.projectAssignment);
        },
        error: (err) => {
          this.snackBar.open(`Error loading employee: ${err.message}`, 'Close', { duration: 5000, });
          this.router.navigate(['/employees']);
        },
      });

      this.accountService.loadCurrentUserDetailed().subscribe({
        next: (employee) => {
          this.employee = employee;
          console.log(employee);
          console.log(this.employee.projectAssignment);
        },
        error: (err) => {
          this.snackBar.open(`Error loading employee: ${err.message}`, 'Close', { duration: 5000, });
          this.router.navigate(['/employees']);
        },
      });
    } else {
      // It's a numeric ID
      this.id = Number(lastSegment);

      if (this.id && this.id > 0) {
        this.loadEmployee();
      } else {
        this.snackBar.open('Invalid employee ID', 'Close', { duration: 3000 });
        this.router.navigate(['/dashboard']);
      }
    }
    
  }

  loadEmployee() {
    if (!this.id) return;

    this.employeeService.getByIdDetailed(this.id).subscribe({
      next: (employee) => {
        this.employee = employee;
      },
      error: (err) => {
        this.snackBar.open(`Error loading employee: ${err.message}`, 'Close', {
          duration: 5000,
        });
        this.router.navigate(['/employees']);
      },
    });
  }

  goToEdit() {
    this.router.navigate([`/employees/edit/${this.id}`]);
  }

  isCurrentUser(): boolean {
    return this.id === this.accountService.currentUser()?.id;
  }
}
