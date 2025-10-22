import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AwaitingResponse } from './components/awaiting-response/awaiting-response';
import { Vacations } from './components/vacations/vacations';
import { adminGuard } from '../shared/guards/admin-guard';
import { MyVacations } from './components/my-vacations/my-vacations';
import { employeeGuard } from '../shared/guards/employee-guard';

const routes: Routes = [
  {
    path: '',
    component: Vacations
  },
  {
    path: 'awaiting-response',
    component: AwaitingResponse
  },
  {
    path: 'my-vacations',
    component: MyVacations
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VacationsRoutingModule { }
