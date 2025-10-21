import { Component } from '@angular/core';
import { VacationShowDto } from '../../vacation-dto/vacation-show-dto';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-awaiting-response',
  imports: [CommonModule],
  templateUrl: './awaiting-response.html',
  styleUrl: './awaiting-response.scss'
})
export class AwaitingResponse {

  vacationShowDtoList: VacationShowDto[] = [];

  constructor(private http: HttpClient) {}

  getAwaitingRequest() {
    this.http.get<VacationShowDto[]>('http://localhost:8081/vacations/awaiting-response'
    ).subscribe(response => {
      this.vacationShowDtoList = response;
      console.log(this.vacationShowDtoList);
    });
  }

}
