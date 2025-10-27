import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { EmployeeService } from '../../service/employee-service';
import { UserDto } from '../../employee-dto/UserDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employees',
  imports: [CommonModule],
  templateUrl: './employees.html',
  styleUrl: './employees.scss',
})
export class Employees {
  protected userService = inject(EmployeeService);
  router = inject(Router);

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
    // this.router.parseUrl('/dashboard')
  }
}
