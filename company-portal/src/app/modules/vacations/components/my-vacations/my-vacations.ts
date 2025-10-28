import { Component, inject, OnInit } from '@angular/core';
import { VacationService } from '../../service/vacation-service';
import { VacationShowDto } from '../../vacation-dto/vacation-show-dto';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-my-vacations',
  imports: [CommonModule],
  templateUrl: './my-vacations.html',
  styleUrl: './my-vacations.scss',
})
export class MyVacations implements OnInit {

  vacationShowDtoList: VacationShowDto[] = [];
  private vacationService = inject(VacationService);

  ngOnInit() {
    this.vacationService.getMyVacations().subscribe({
      next: (list) => {
        this.vacationShowDtoList = (list ?? []).map(v => ({
          ...v,
          startDate: new Date(v.startDate),
          endDate: new Date(v.endDate),
        }));
      },
      error: (err) => {
        console.error('Failed to load vacations', err);
      }
    });
  }

}
