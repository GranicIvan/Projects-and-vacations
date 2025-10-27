import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { UserDto } from '../employee-dto/UserDto';
import { CreateUserDto } from '../employee-dto/CreateUserDto';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseUrl = environment.apiUrl + 'users';

  constructor(private http: HttpClient) {}



  getUsers() {
    return this.http.get<CreateUserDto[]>(`${this.baseUrl}`, { withCredentials: true });
  }

  createEmployee(employee: Partial<CreateUserDto>) {
    return this.http.post<CreateUserDto>(`${this.baseUrl}`, employee, { withCredentials: true }).toPromise();
  }
}
