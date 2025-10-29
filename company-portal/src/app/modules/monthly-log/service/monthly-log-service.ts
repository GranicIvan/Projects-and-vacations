import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { MonthlyLogDto } from '../monthly-log-dto/monthly-log-dto';
import { MonthlyEarningsByProject } from '../monthly-log-dto/monthly-earnings-by-project';

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

  getMyLoggedHours() {
    return this.http.get<MonthlyLogDto[]>(`${this.baseUrl}/myMonthlyLogs`, { 
      withCredentials: true 
    });
  }

  getAllLoggedHours() {
    return this.http.get<MonthlyLogDto[]>(`${this.baseUrl}`, { 
      withCredentials: true 
    });
  }

  getLogsByProject(projectId: number) {
    return this.http.get<MonthlyLogDto[]>(`${this.baseUrl}/monthlyLogsForProject/${projectId}`, { 
      withCredentials: true 
    });
  }

  getLogsByYearMonthGroupedByProject(yearMonth: string) {
    return this.http.get<MonthlyEarningsByProject[]>(`${this.baseUrl}/monthlyLogsForMonthByProjects/${yearMonth}`, {
      withCredentials: true
    });
  }

  

  getLogsByYearGroupedByProject(year: number) {
    return this.http.get<MonthlyEarningsByProject[]>(`${this.baseUrl}/monthlyLogsForYearByProjects/${year}`, {
      withCredentials: true
    });
  }


  deleteLog(logId: number) {
    return this.http.delete<void>(`${this.baseUrl}/${logId}`, { 
      withCredentials: true 
    });
  }
}
