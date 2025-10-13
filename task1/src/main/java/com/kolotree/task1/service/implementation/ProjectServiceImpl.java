package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.project.ProjectCreateDto;
import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.mapper.ProjectMapper;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.repository.ProjectRepository;
import com.kolotree.task1.service.interfaces.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Override
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getOne(Integer id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project addProject(ProjectCreateDto projectCreateDto) {
        Project project = ProjectMapper.createDtoToProject(projectCreateDto);
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Integer id) {

        Optional<Project> optionalProject = projectRepository.findById(id);

        if(optionalProject.isEmpty()){
            throw new EntityNotFoundException("Project with ID " + id + " not found");
        }

        Project project = optionalProject.get();

        if(project.getProjectAssignment().isEmpty()){
            projectRepository.deleteById(id);
        }else{
            projectRepository.updateActiveStatus(id, false);
        }

        projectRepository.deleteById(id);
    }

    @Override
    public Project updateProject(Integer id, Project updatedProject) {
        var existing = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        updatedProject.setId(existing.getId());
        return projectRepository.save(updatedProject);
    }

    @Override
    public Project patchProject(Integer id, ProjectPatchDto dto) {

        var existing = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        if (dto.getProjectName() == null && dto.getDescription() == null) {
            throw new IllegalArgumentException("At least one field must be provided.");
        }

        if (dto.getProjectName() != null && dto.getProjectName().isBlank()) {
            throw new IllegalArgumentException("projectName must not be blank when provided.");
        }

        ProjectMapper.applyPatch(existing, dto);
        return projectRepository.save(existing);
    }
}
