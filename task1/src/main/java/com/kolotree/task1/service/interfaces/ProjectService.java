package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.project.ProjectCreateDto;
import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.dto.project.ProjectShowDto;
import com.kolotree.task1.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Iterable<Project> findAll();

    Optional<Project> getOne(Integer id);


    Project addProject(ProjectCreateDto project);

    void deleteProject(Integer id);

    Project updateProject(Integer id, Project updatedProject);

    Project patchProject(Integer id, ProjectPatchDto dto);

    List<ProjectShowDto> getAllWithUser(Integer id);
}
