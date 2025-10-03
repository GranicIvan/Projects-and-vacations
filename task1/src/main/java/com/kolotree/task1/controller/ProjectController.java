package com.kolotree.task1.controller;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.mapper.ProjectMapper;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.repository.ProjectRepo;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepo projectRepo;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }


    @GetMapping
    public Iterable<Project> getAll(){
        return projectRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Integer id) {
        return projectRepo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Project addProject(@RequestBody Project project){
        return projectRepo.save(project);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id){
        if (!projectRepo.existsById(id)) return ResponseEntity.notFound().build();
        projectRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject) {
    if (!projectRepo.existsById(id)) return ResponseEntity.notFound().build();

    Project existingProject = projectRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        updatedProject.setId(existingProject.getId());
        Project savedProject = projectRepo.save(updatedProject);
        return ResponseEntity.ok(savedProject);
    
    }

    @PatchMapping("/{id}")
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
