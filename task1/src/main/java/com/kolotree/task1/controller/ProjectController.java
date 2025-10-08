package com.kolotree.task1.controller;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.service.interfaces.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectServiceImpl;


    @GetMapping
    public Iterable<Project> getAll() {

        return ResponseEntity.ok( projectServiceImpl.findAll()).getBody();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Integer id) {
        return projectServiceImpl.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        Project saved = projectServiceImpl.addProject(project);
        return ResponseEntity.ok(saved).getBody();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {

        try {
            projectServiceImpl.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject) {
        try {
            Project saved = projectServiceImpl.updateProject(id, updatedProject);
            return ResponseEntity.ok(saved);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProject(@PathVariable Integer id,
                                          @Valid @RequestBody ProjectPatchDto dto) {
        try {
            Project patched = projectServiceImpl.patchProject(id, dto);
            return ResponseEntity.ok(patched);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
