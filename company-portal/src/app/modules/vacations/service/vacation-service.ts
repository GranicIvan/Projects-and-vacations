import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { VacationShowDto } from '../vacation-dto/vacation-show-dto';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VacationService {

  private baseUrl = environment.apiUrl + 'vacations/';

  constructor(private http: HttpClient) {}

  getAwaitingResponse() {
    return this.http.get<VacationShowDto[]>(this.baseUrl + 'getAwaitingVacationRequests', {
      withCredentials: true,
    });
  }

  async requestVacation(arg0: { startDate: string; endDate: string }): Promise<VacationShowDto> {
    const response = await lastValueFrom(
      this.http.post<VacationShowDto>(this.baseUrl + 'requestVacation', arg0, { withCredentials: true })
    );
    console.log("VS:", response);
    return response;
  }
}
