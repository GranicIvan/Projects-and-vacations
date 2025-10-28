import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ProjectShowDto } from '../project-dto/ProjectShowDto';
import { CreateProjectDto } from '../project-dto/CreateProjectDto';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
    private baseUrl = environment.apiUrl + 'projects';

  constructor(private http: HttpClient) {}

  getAllProjects() {
    return this.http.get<ProjectShowDto[]>(this.baseUrl, { withCredentials: true });
  }


  addProject(projectData: { projectName: string; description: string }) {
    return this.http.post<ProjectShowDto>(this.baseUrl , projectData, {
      withCredentials: true,
      headers: { 'Content-Type': 'application/json' },
    });
  }

  updateProject(project:  CreateProjectDto & { id: number }) {
    return this.http.patch<ProjectShowDto>(`${this.baseUrl}/${project.id}`, project, {
      withCredentials: true,
    });
  }

  deleteProject(projectId: number) { 
    return this.http.delete<void>(`${this.baseUrl}/${projectId  }`, { withCredentials: true });
  }
}
