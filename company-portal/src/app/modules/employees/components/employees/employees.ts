import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { EmployeeService } from '../../service/employee-service';
import { UserDto } from '../../employee-dto/UserDto';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-employees',
  imports: [CommonModule],
  templateUrl: './employees.html',
  styleUrl: './employees.scss',
})
export class Employees {
  protected userService = inject(EmployeeService);
  router = inject(Router);
  private snackBar = inject(MatSnackBar);

  userShowDtoList: UserDto[] = [];

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers().subscribe((users) => {
      this.userShowDtoList = users;
    });
  }

  addUser() {
    this.router.navigate(['/employees/add-employee']);
  }

  editUser(userId: number) {
    this.router.navigate([`/employees/edit/${userId}`]);
  }

  deleteUser(userId: number) {
    this.userService.deleteUser(userId).subscribe(() => {
      this.getUsers();
    });
    this.snackBar.open('Employee deleted', 'Close', { duration: 5000 });
  }
}
