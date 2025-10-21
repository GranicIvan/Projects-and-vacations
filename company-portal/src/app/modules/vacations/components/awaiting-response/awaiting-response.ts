import { Component, inject } from '@angular/core';
import { VacationShowDto } from '../../vacation-dto/vacation-show-dto';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { VacationService } from '../../service/vacation-service';

@Component({
  selector: 'app-awaiting-response',
  imports: [CommonModule],
  templateUrl: './awaiting-response.html',
  styleUrl: './awaiting-response.scss'
})
export class AwaitingResponse {

  vacationShowDtoList: VacationShowDto[] = [];

  constructor(private http: HttpClient) {}
  protected vacationService = inject(VacationService);

  getAwaitingRequest() {
    this.vacationService.getAwaitingResponse().subscribe(response => {
      this.vacationShowDtoList = response;
      
      for (let v of this.vacationShowDtoList) {
        v.startDate = new Date(v.startDate);
        v.endDate = new Date(v.endDate);
        console.log(v);
      }

      console.log(this.vacationShowDtoList);
    });
  }

}
