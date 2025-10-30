import { Component, inject } from '@angular/core';
import { VacationShowDto } from '../../vacation-dto/vacation-show-dto';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { VacationService } from '../../service/vacation-service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-awaiting-response',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './awaiting-response.html',
  styleUrl: './awaiting-response.scss',
})
export class AwaitingResponse {
  vacationShowDtoList: VacationShowDto[] = [];
  private snackBar = inject(MatSnackBar);

  protected vacationService = inject(VacationService);

  ngOnInit() {
    this.getAwaitingRequest();
  }

  getAwaitingRequest() {
    this.vacationService.getAwaitingResponse().subscribe((response) => {
      this.vacationShowDtoList = response;

      for (let v of this.vacationShowDtoList) {
        v.startDate = new Date(v.startDate);
        v.endDate = new Date(v.endDate);
      }
    });
  }

  approveVacationRequest(vacationId: number) {
    this.vacationService.approveVacationRequest(vacationId).subscribe(() => {
      this.snackBar.open('Vacation request approved', 'Close', { duration: 5000 });
      this.getAwaitingRequest();
    });
      
    
  }

  denyVacationRequest(vacationId: number) {
    this.vacationService.denyVacationRequest(vacationId).subscribe(() => {
      this.snackBar.open('Vacation request denied', 'Close', { duration: 5000 });
      this.getAwaitingRequest();
    });
  }
}
