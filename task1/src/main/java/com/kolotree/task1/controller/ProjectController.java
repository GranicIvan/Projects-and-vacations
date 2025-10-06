package com.kolotree.task1.controller;

import com.kolotree.task1.Service.ProjectService;
import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Iterable<Project> getAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Integer id) {
        return projectService.getOne(id);
    }


    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        return projectService.deleteProject(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProject(@PathVariable Integer id,
                                          @Valid @RequestBody ProjectPatchDto dto) {
        return projectService.patchProject(id, dto);
    }


}
