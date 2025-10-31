import { Component, inject, OnInit } from '@angular/core';
import { VacationShowDto } from '../../vacation-dto/vacation-show-dto';
import { VacationService } from '../../service/vacation-service';
import { CommonModule } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-vacations',
  imports: [CommonModule],
  templateUrl: './vacations.html',
  styleUrl: './vacations.scss',
})
export class Vacations implements OnInit {
  vacationShowDtoList: VacationShowDto[] = [];

  protected vacationService = inject(VacationService);

  private snackBar = inject(MatSnackBar);

  ngOnInit() {
    this.getAllRequest();
  }

  getAllRequest() {
    this.vacationService.getAllVacations().subscribe((response) => {
      this.vacationShowDtoList = response;

      for (const v of this.vacationShowDtoList) {
        v.startDate = new Date(v.startDate);
        v.endDate = new Date(v.endDate);        
      }
    });
  }

  approveVacationRequest(vacationId: number) {
    this.vacationService.approveVacationRequest(vacationId).subscribe(() => {
      this.snackBar.open('Vacation request approved', 'Close', { duration: 5000 });
      this.getAllRequest();
    });
  }
  

  denyVacationRequest(vacationId: number) {
    this.vacationService.denyVacationRequest(vacationId).subscribe(() => {
      this.snackBar.open('Vacation request denied', 'Close', { duration: 5000 });
      this.getAllRequest();
    });
  }
}
