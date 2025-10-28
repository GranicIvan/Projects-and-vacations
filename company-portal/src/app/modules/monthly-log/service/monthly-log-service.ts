import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MonthlyLogService {

  private baseUrl = environment.apiUrl + 'monthlyLog';

  constructor(private http: HttpClient) {}

  logWorkedHours(projectId: number, hoursWorked: number, yearMonth: string) {
    const body = { projectId, hoursWorked, yearMonth };
    return this.http.post(this.baseUrl + '/addHoursToProjectForMonth', body, {
      withCredentials: true,
      responseType: 'text'
    });
  }
}
