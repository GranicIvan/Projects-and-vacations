package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.model.Project;
import org.springframework.http.ResponseEntity;

public interface ProjectService {

    Iterable<Project> findAll();

    ResponseEntity<Project> getOne(Integer id);

    Project addProject(Project project);

    ResponseEntity<Void> deleteProject(Integer id);

    ResponseEntity<Project> updateProject(Integer id, Project updatedProject);

    ResponseEntity<?> patchProject(Integer id, ProjectPatchDto dto);
}
