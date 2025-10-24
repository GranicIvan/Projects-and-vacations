import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AwaitingResponse } from './components/awaiting-response/awaiting-response';
import { Vacations } from './components/vacations/vacations';
import { adminGuard } from '../shared/guards/admin-guard';
import { MyVacations } from './components/my-vacations/my-vacations';
import { employeeGuard } from '../shared/guards/employee-guard';
import { RequestVacation } from './components/request-vacation/request-vacation';

const routes: Routes = [
  {
    path: '',
    component: Vacations,
    canActivate: [adminGuard],
  },
  {
    path: 'awaiting-response',
    component: AwaitingResponse,
    canActivate: [adminGuard],
  },
  {
    path: 'my-vacations',
    component: MyVacations,
    canActivate: [employeeGuard],
  },
  {
    path: 'request-a-vacation',
    component: RequestVacation,
    canActivate: [employeeGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VacationsRoutingModule {}
