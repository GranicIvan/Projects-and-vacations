import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { LoginDto } from '../../employees/employee-dto/LoginDto';
import { UserDto } from '../../employees/employee-dto/UserDto';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseUrl = environment.apiUrl + 'auth/';

  constructor(private http: HttpClient) {}

  login(loginCreds: LoginDto) {
    return this.http.post<UserDto>(this.baseUrl + 'login', loginCreds, { withCredentials: true });
  }

}
