import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { VacationShowDto } from '../vacation-dto/vacation-show-dto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class VacationService {
  private baseUrl = environment.apiUrl + 'vacations';

  constructor(private http: HttpClient) {}

  getAwaitingResponse() {
    return this.http.get<VacationShowDto[]>(this.baseUrl + '/getAwaitingVacationRequests', {
      withCredentials: true,
    });
  }

  getAllVacations() {
    return this.http.get<VacationShowDto[]>(this.baseUrl, {
      withCredentials: true,
    });
  }

  requestVacation(arg0: { startDate: string; endDate: string }) {
    return this.http.post<VacationShowDto>(this.baseUrl + '/requestVacation', arg0, {
      withCredentials: true,
    });
  }

  getMyVacations() {
    return this.http.get<VacationShowDto[]>(this.baseUrl + '/myVacations', {
      withCredentials: true,
    });
  }

  approveVacationRequest(vacationId: number) {
    return this.http.post<string>(
      this.baseUrl + '/setVacationRequestStatus',
      {
        id: vacationId,
        vacationRequestStatus: 'APPROVED',
      },
      {
        withCredentials: true,
        headers: { 'Content-Type': 'application/json', 
          },
      },
    );
  }

  denyVacationRequest(vacationId: number) {

      return this.http.post<void>(
        this.baseUrl + '/setVacationRequestStatus',
        {
          id: vacationId,
          vacationRequestStatus: 'DENIED',
        },
        {
          withCredentials: true,
        },
      );
  }
  
  
}
