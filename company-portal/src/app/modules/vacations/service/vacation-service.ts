import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { VacationShowDto } from '../vacation-dto/vacation-show-dto';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

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

  async requestVacation(arg0: { startDate: string; endDate: string }): Promise<VacationShowDto> {
    const response = await lastValueFrom(
      this.http.post<VacationShowDto>(this.baseUrl + '/requestVacation', arg0, {
        withCredentials: true,
      }),
    );
    return response;
  }

  async getMyVacations(): Promise<VacationShowDto[]> {
    const response = await lastValueFrom(
      this.http.get<VacationShowDto[]>(this.baseUrl + '/myVacations', {
        withCredentials: true,
      }),
    );
    return response;
  }

  async approveVacationRequest(vacationId: number): Promise<void> {
    await lastValueFrom(
      this.http.post<void>(
        this.baseUrl + '/setVacationRequestStatus',
        {
          id: vacationId,
          vacationRequestStatus: 'APPROVED',
        },
        {
          withCredentials: true,
          headers: { 'Content-Type': 'application/json' },
        },
      ),
    );
  }

  async denyVacationRequest(vacationId: number): Promise<void> {
    await lastValueFrom(
      this.http.post<void>(
        this.baseUrl + '/setVacationRequestStatus',
        {
          id: vacationId,
          vacationRequestStatus: 'DENIED',
        },
        {
          withCredentials: true,
        },
      ),
    );
  }
}
