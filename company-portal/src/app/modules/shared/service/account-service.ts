import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { LoginDto } from '../../employees/employee-dto/LoginDto';
import { UserDto } from '../../employees/employee-dto/UserDto';
import { environment } from '../../../environments/environment';
import { Observable, tap } from 'rxjs';
import { DetailedUserDto } from '../../employees/employee-dto/DetailedUserDto';

@Injectable({
  providedIn: 'root',
})
export class AccountService {

  private baseAuthUrl = environment.apiUrl + 'auth/';
  private baseUserUrl = environment.apiUrl + 'users/';

  constructor(private http: HttpClient) {
  }

  currentUser = signal<UserDto | null>(null);

  login(loginCreds: LoginDto) {
    const response = this.http
      .post<UserDto>(this.baseAuthUrl + 'login', loginCreds, { withCredentials: true })
      .pipe(
        tap(() => {
          this.loadCurrentUser().subscribe((user) => {
            this.currentUser.set(user);
          });
        }),
      );

    return response;
  }

  isLoggedIn() {
    return this.currentUser() !== null;
  }

  loadCurrentUser() {
    const response = this.http.get<UserDto>(this.baseUserUrl + 'meSlim', { withCredentials: true });
    return response;
    
  }loadCurrentUserDetailed() {
    const response = this.http.get<DetailedUserDto>(this.baseUserUrl + 'meWithProjects', { withCredentials: true });
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

    isAdmin() {
    const user = this.currentUser();
    return user ? user.userRole == 'ADMIN' : false;
  }

    isEmployee() {
    const user = this.currentUser();
    return user ? user.userRole == 'EMPLOYEE' : false;
  }

    init(): Observable<UserDto> {
    return this.loadCurrentUser().pipe(
      tap((user) => {
        if (user) {
          this.currentUser.set(user);
        }
      }),
    );
  }
  
}
