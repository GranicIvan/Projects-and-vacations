import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { EmployeeService } from '../../service/employee-service';
import { UserDto } from '../../employee-dto/UserDto';

@Component({
  selector: 'app-employees',
  imports: [CommonModule],
  templateUrl: './employees.html',
  styleUrl: './employees.scss',
})
export class Employees {
  protected userService = inject(EmployeeService);

  userShowDtoList: UserDto[] = [];

  ngOnInit() { 
    console.log("ngOnInit called");
    this.getUsers();
    console.log(this.userShowDtoList);
  }

  getUsers() {
    this.userService.getUsers().subscribe((users) => {
      this.userShowDtoList = users;
    });
  }
}
