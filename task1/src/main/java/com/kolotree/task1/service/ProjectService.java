package com.kolotree.task1.service;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.mapper.ProjectMapper;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.repository.ProjectRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    public Iterable<Project> findAll() {
        return projectRepo.findAll();
    }

    public ResponseEntity<Project> getOne(@PathVariable Integer id) {
        return projectRepo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Project addProject(@RequestBody Project project) {
        return projectRepo.save(project);
    }

    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        if (!projectRepo.existsById(id)) return ResponseEntity.notFound().build();
        projectRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject) {
        if (!projectRepo.existsById(id)) return ResponseEntity.notFound().build();

        Project existingProject = projectRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        updatedProject.setId(existingProject.getId());
        Project savedProject = projectRepo.save(updatedProject);
        return ResponseEntity.ok(savedProject);

    }

    public ResponseEntity<?> patchProject(@PathVariable Integer id,
                                          @Valid @RequestBody ProjectPatchDto dto) {

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
