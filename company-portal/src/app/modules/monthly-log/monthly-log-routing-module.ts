import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogWorkedHours } from './components/log-worked-hours/log-worked-hours';
import { employeeGuard } from '../shared/guards/employee-guard';
import { MyLoggedHours } from './components/my-logged-hours/my-logged-hours';
import { EarningsPerProject } from './components/earnings-per-project/earnings-per-project';
import { adminGuard } from '../shared/guards/admin-guard';
import { EarningsPerEmployee } from './components/earnings-per-employee/earnings-per-employee';


const routes: Routes = [

  {
    path: 'log-work-hours/:id',
    component: LogWorkedHours,
    canActivate: [employeeGuard]
  },
  {
    path: 'my-logged-hours/:id',
    component: MyLoggedHours,
    canActivate: [employeeGuard]
  },
  {
    path: 'per-project',
    component: EarningsPerProject,
    canActivate: [adminGuard]
  },
  {
    path: 'per-employee',
    component: EarningsPerEmployee,
    canActivate: [adminGuard]
  }
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MonthlyLogRoutingModule { }
