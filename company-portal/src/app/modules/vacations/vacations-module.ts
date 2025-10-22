import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacationsRoutingModule } from './vacations-routing-module';
import { Vacations } from './components/vacations/vacations';
import { AwaitingResponse } from './components/awaiting-response/awaiting-response';
import { MyVacations } from './components/my-vacations/my-vacations';

@NgModule({
  imports: [
    CommonModule,
    VacationsRoutingModule,
    Vacations,
    AwaitingResponse,
    MyVacations
  ]
})
export class VacationsModule { }
