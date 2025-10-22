import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Employees } from './components/employees/employees';
import { EditEmployee } from './components/edit-employee/edit-employee';
import { NewEmployee } from './components/new-employee/new-employee';

const routes: Routes = [
  {
    path: '',
    component: Employees
  },
  {
    path: 'new',
    component: NewEmployee
  },
  {
    path: 'edit/:id',
    component: EditEmployee
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeesRoutingModule { }
