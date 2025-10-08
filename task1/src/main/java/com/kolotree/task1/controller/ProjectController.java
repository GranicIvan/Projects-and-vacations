package com.kolotree.task1.controller;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.service.interfaces.ProjectService;
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
        return projectServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Integer id) {
        return projectServiceImpl.getOne(id);
    }


    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectServiceImpl.addProject(project);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        return projectServiceImpl.deleteProject(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject) {
        return projectServiceImpl.updateProject(id, updatedProject);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProject(@PathVariable Integer id,
                                          @Valid @RequestBody ProjectPatchDto dto) {
        return projectServiceImpl.patchProject(id, dto);
    }


}
