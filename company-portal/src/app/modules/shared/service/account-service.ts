import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { LoginDto } from '../../employees/employee-dto/LoginDto';
import { UserDto } from '../../employees/employee-dto/UserDto';
import { environment } from '../../../environments/environment';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private baseAuthUrl = environment.apiUrl + 'auth/';
  private baseUserUrl = environment.apiUrl + 'users/';

  constructor(private http: HttpClient) {}

  currentUser = signal<UserDto | null>(null);

  login(loginCreds: LoginDto) {
    let response = this.http
      .post<UserDto>(this.baseAuthUrl + 'login', loginCreds, { withCredentials: true })
      .pipe(
        tap(() => {
          this.getCurrentUser().subscribe((user) => {
            this.currentUser.set(user);
          });
        }),
      );

    return response;
  }

  isLoggedIn() {
    return this.currentUser() !== null;
  }

  getCurrentUser() {
    const response = this.http.get<UserDto>(this.baseUserUrl + 'meSlim', { withCredentials: true });
    return response;
  }

  fetchUserByEmail(email: string) {}

  logout() {
    this.http
      .post(this.baseAuthUrl + 'logout', {}, { withCredentials: true })
      .pipe(tap(() => this.clearCurrentUser()))
      .subscribe();
  }

  clearCurrentUser() {
    this.currentUser.set(null);
  }
}
