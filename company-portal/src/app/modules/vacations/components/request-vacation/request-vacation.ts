import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { VacationService } from '../../service/vacation-service';
import { MatSnackBar } from '@angular/material/snack-bar';

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

  private snackBar = inject(MatSnackBar);

  readonly requestVacationForm: RequestVacationForm = this.fb.group({
    startDate: this.fb.control('', {
      validators: [Validators.required, ],
    }),
    endDate: this.fb.control('', {
      validators: [Validators.required],
    }),
  });

  protected vacationService = inject(VacationService);

  async requestVacation() {
    if (this.requestVacationForm.invalid) return;

    try {
      const response = await this.vacationService.requestVacation(this.requestVacationForm.getRawValue());
      this.requestVacationForm.reset();
      this.snackBar.open('Vacation requested successfully', 'Close', { duration: 5000 });
    } catch (error) {
      this.snackBar.open('Failed to request vacation', 'Close', { duration: 5000 });
      console.error(error);
    }
  }

}
