package com.kolotree.task1.controller;

import com.kolotree.task1.dto.project.ProjectCreateDto;
import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.dto.project.ProjectShowDto;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.service.interfaces.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;


    @GetMapping
    public Iterable<Project> getAll() {

        return ResponseEntity.ok(projectService.findAll()).getBody();
    }


    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/forUser/{id}")
    public ResponseEntity<List<ProjectShowDto>> forEmployee(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getAllWithUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Integer id) {
        return projectService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Project addProject(@RequestBody ProjectCreateDto project) {
        Project saved = projectService.addProject(project);
        return ResponseEntity.ok(saved).getBody();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {

        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject) {
        try {
            Project saved = projectService.updateProject(id, updatedProject);
            return ResponseEntity.ok(saved);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProject(@PathVariable Integer id,
                                          @Valid @RequestBody ProjectPatchDto dto) {
        try {
            Project patched = projectService.patchProject(id, dto);
            return ResponseEntity.ok(patched);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
