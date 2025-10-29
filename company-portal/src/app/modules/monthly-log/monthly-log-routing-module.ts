import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogWorkedHours } from './components/log-worked-hours/log-worked-hours';
import { employeeGuard } from '../shared/guards/employee-guard';
import { MyLoggedHours } from './components/my-logged-hours/my-logged-hours';


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
  }
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MonthlyLogRoutingModule { }
