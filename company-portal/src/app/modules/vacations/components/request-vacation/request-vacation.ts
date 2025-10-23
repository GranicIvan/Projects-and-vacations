import { Component, inject } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { VacationService } from '../../service/vacation-service';

@Component({
  selector: 'app-request-vacation',
  imports: [],
  templateUrl: './request-vacation.html',
  styleUrl: './request-vacation.scss'
})
export class RequestVacation {

  private fb = inject(NonNullableFormBuilder);

  protected vacationService = inject(VacationService);
  requestVacation() {

  }

}
