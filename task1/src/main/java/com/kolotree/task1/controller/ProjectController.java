package com.kolotree.task1.controller;

import com.kolotree.task1.model.Project;
import com.kolotree.task1.repository.ProjectRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepo projectRepo;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }


    @GetMapping("/getAll")
    public Iterable<Project> getAll(){
        return projectRepo.findAll();
    }

    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project project){
        System.out.println("PROJECT IS: " + project);
        return projectRepo.save(project);
    }

    @GetMapping("/testP")
    public String testP(){
        return "works";
    }



}
