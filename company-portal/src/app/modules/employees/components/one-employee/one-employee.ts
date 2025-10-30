import { Component, inject, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../service/employee-service';
import { UserDto } from '../../employee-dto/UserDto';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { AccountService } from '../../../shared/service/account-service';

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

  id: number = -1;
  @Input() input: string | undefined;

  employee: UserDto | null = null;

  ngOnInit() {
    // Method 1: Get the full URL and split it
    const urlSegments = this.router.url.split('/');
    const lastSegment = urlSegments[urlSegments.length - 1];

    console.log('Last URL segment:', lastSegment); // Will be 'me' or a number

    if (lastSegment === 'me') {
      // Load current user's data
      this.employee = this.accountService.currentUser();
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

    this.employeeService.getById(this.id).subscribe({
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
