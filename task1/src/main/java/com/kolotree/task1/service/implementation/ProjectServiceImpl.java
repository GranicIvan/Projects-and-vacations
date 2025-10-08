package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.mapper.ProjectMapper;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.repository.ProjectRepo;
import com.kolotree.task1.service.interfaces.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepo projectRepo;

    @Override
    public Iterable<Project> findAll() {
        return projectRepo.findAll();
    }

    @Override
    public ResponseEntity<Project> getOne( Integer id) {
        return projectRepo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Project addProject( Project project) {
        return projectRepo.save(project);
    }

    @Override
    public ResponseEntity<Void> deleteProject( Integer id) {
        if (!projectRepo.existsById(id)) return ResponseEntity.notFound().build();
        projectRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Project> updateProject(Integer id,  Project updatedProject) {
        if (!projectRepo.existsById(id)) return ResponseEntity.notFound().build();

        Project existingProject = projectRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        updatedProject.setId(existingProject.getId());
        Project savedProject = projectRepo.save(updatedProject);
        return ResponseEntity.ok(savedProject);

    }

    @Override
    public ResponseEntity<?> patchProject(Integer id, ProjectPatchDto dto) {

        if (dto.getProjectName() == null && dto.getDescription() == null && dto.getMonthlyIncome() == null) {
            return ResponseEntity.badRequest().body("At least one field must be provided.");
        }

        // If projectName is provided, ensure it's not blank
        if (dto.getProjectName() != null && dto.getProjectName().isBlank()) {
            return ResponseEntity.badRequest().body("projectName must not be blank when provided.");
        }

        return projectRepo.findById(id)
                .map(existing -> {
                    ProjectMapper.applyPatch(existing, dto);
                    Project saved = projectRepo.save(existing);
                    return ResponseEntity.ok(ProjectMapper.toResponse(saved));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
