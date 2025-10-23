import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { VacationService } from '../../service/vacation-service';

type RequestVacationForm = FormGroup<{
  startDate: FormControl<string>;
  endDate: FormControl<string>;
}>;

@Component({
  selector: 'app-request-vacation',
  imports: [ReactiveFormsModule],
  templateUrl: './request-vacation.html',
  styleUrl: './request-vacation.scss'
})
export class RequestVacation {

  private fb = inject(NonNullableFormBuilder);

  readonly requestVacationForm: RequestVacationForm = this.fb.group({
    startDate: this.fb.control('', {
      validators: [Validators.required, ],
    }),
    endDate: this.fb.control('', {
      validators: [Validators.required],
    }),
  });

  protected vacationService = inject(VacationService);

  requestVacation() {
    if (this.requestVacationForm.invalid) return;

    this.vacationService.requestVacation(this.requestVacationForm.getRawValue()).subscribe({
      next: () => {
        this.requestVacationForm.reset();      
        // TODO handle success appropriately  
      },
      error: () => {
        // TODO handle error appropriately
      }
    });
  }

}
