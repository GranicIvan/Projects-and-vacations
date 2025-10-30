import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { UserDto } from '../employee-dto/UserDto';
import { CreateUserDto } from '../employee-dto/CreateUserDto';
import { DetailedUserDto } from '../employee-dto/DetailedUserDto';

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
    return this.http.post<CreateUserDto>(`${this.baseUrl}`, employee, { withCredentials: true });
  }

  deleteUser(userId: number) {
    return this.http.delete<void>(`${this.baseUrl}/${userId}`, { withCredentials: true });
  }

  updateEmployee(employee: Partial<UserDto>) {
    return this.http.patch<UserDto>(`${this.baseUrl}/${employee.id}`, employee, { withCredentials: true });
  }

  getById(userId: number) {
    return this.http.get<UserDto>(`${this.baseUrl}/${userId}`, { withCredentials: true });
  }


  getByIdDetailed(userId: number) {
    return this.http.get<DetailedUserDto>(`${this.baseUrl}/${userId}`, { withCredentials: true });
  }
}
