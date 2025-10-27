import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Projects } from './components/projects/projects';
import { adminGuard } from '../shared/guards/admin-guard';
import { AddProject } from './components/add-project/add-project';
import { AddEmployeeToProject } from './components/add-employee-to-project/add-employee-to-project';
import { EditEmployee } from '../employees/components/edit-employee/edit-employee';
import { EditProject } from './components/edit-project/edit-project';

const routes: Routes = [
  {path: '',
    component: Projects,
    canActivate: [adminGuard]
  },
  {path: 'new',
    component: AddProject,
    canActivate: [adminGuard]
  },
  {path: 'edit/:id',
    component: EditProject,
    canActivate: [adminGuard]
  },
  {
    path: 'add-employee-to-project',
    component: AddEmployeeToProject,
    canActivate: [adminGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectsRoutingModule { }
