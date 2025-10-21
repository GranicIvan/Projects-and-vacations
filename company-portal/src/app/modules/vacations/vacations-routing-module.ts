import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AwaitingResponse } from './components/awaiting-response/awaiting-response';
import { Vacations } from './components/vacations/vacations';

const routes: Routes = [
  {
    path: '',
    component: Vacations
  },
  {
    path: 'awaiting-response',
    component: AwaitingResponse
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VacationsRoutingModule { }
