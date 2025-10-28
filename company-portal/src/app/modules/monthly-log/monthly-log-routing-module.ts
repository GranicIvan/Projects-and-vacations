import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogWorkedHours } from './components/log-worked-hours/log-worked-hours';
import { employeeGuard } from '../shared/guards/employee-guard';


const routes: Routes = [

  {path: 'log-work-hours',
    component: LogWorkedHours,
    canActivate: [employeeGuard]
  },
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MonthlyLogRoutingModule { }
