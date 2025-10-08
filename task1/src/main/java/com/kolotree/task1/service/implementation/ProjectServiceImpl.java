package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.mapper.ProjectMapper;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.repository.ProjectRepo;
import com.kolotree.task1.service.interfaces.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepo projectRepo;

    @Override
    public Iterable<Project> findAll() {
        return projectRepo.findAll();
    }

    @Override
    public Optional<Project> getOne(Integer id) {
        return projectRepo.findById(id);
    }

    @Override
    public Project addProject( Project project) {
        return projectRepo.save(project);
    }

    @Override
    public void deleteProject( Integer id) {
        if (!projectRepo.existsById(id)) {
                throw new EntityNotFoundException("Project with ID " + id + " not found");
            }
            projectRepo.deleteById(id);
    }

    @Override
    public Project updateProject(Integer id,  Project updatedProject) {
        var existing = projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        updatedProject.setId(existing.getId());
        return projectRepo.save(updatedProject);
    }

    @Override
    public Project patchProject(Integer id, ProjectPatchDto dto) {

        var existing = projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        if (dto.getProjectName() == null && dto.getDescription() == null && dto.getMonthlyIncome() == null) {
            throw new IllegalArgumentException("At least one field must be provided.");
        }

        if (dto.getProjectName() != null && dto.getProjectName().isBlank()) {
            throw new IllegalArgumentException("projectName must not be blank when provided.");
        }

        ProjectMapper.applyPatch(existing, dto);
        return projectRepo.save(existing);
    }
}
