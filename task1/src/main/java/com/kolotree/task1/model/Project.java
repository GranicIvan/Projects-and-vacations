package com.kolotree.task1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String description;

    @OneToMany(mappedBy = "project")
    private List<UserOnProject> userOnProject = new ArrayList<>();


}
