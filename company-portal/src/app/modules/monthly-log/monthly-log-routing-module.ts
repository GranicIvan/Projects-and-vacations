import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogWorkedHours } from './components/log-worked-hours/log-worked-hours';
import { employeeGuard } from '../shared/guards/employee-guard';
import { MyLoggedHours } from './components/my-logged-hours/my-logged-hours';

import { adminGuard } from '../shared/guards/admin-guard';
import { EarningsPerEmployee } from './components/earnings-per-employee/earnings-per-employee';
import { EarningsReview } from './components/earnings-review/earnings-review';
import { EarningsPerProject } from './components/earnings-per-project/earnings-per-project';
import { TotalEarnings } from './components/total-earnings/total-earnings';


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
    path: 'earning-review',
    component: EarningsReview,
    canActivate: [adminGuard]
  },
  {
    path: 'earnings-per-project',
    component: EarningsPerProject,
    canActivate: [adminGuard] 
  },
  {
    path: 'earnings-per-employee',
    component: EarningsPerEmployee,
    canActivate: [adminGuard]
  },
  {
    path: 'total-earning',
    component: TotalEarnings,
    canActivate: [adminGuard]
  }
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MonthlyLogRoutingModule { }
