import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Employees } from './components/employees/employees';
import { EditEmployee } from './components/edit-employee/edit-employee';
import { NewEmployee } from './components/new-employee/new-employee';
import { adminGuard } from '../shared/guards/admin-guard';
import { OneEmployee } from './components/one-employee/one-employee';
import { employeeGuard } from '../shared/guards/employee-guard';

const routes: Routes = [
  {
    path: '',
    component: Employees,
    canActivate: [adminGuard]
  },
  {
    path: 'new',
    component: NewEmployee,
    canActivate: [adminGuard]
  },
  {
    path: 'edit/:id',
    component: EditEmployee,
    canActivate: [adminGuard]
  },
  {
    path: 'add-employee',
    component: NewEmployee,
    canActivate: [adminGuard]
  },
  {
    path: 'me',
    component: OneEmployee,
    canActivate: [employeeGuard]
  },
  {
    path: ':id',
    component: OneEmployee,
    canActivate: [adminGuard]
  }
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeesRoutingModule { }
