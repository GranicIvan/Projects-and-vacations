package com.kolotree.task1.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String projectName;
    @NonNull
    private String description;
    @NonNull
    private boolean activeStatus;

    @OneToMany(mappedBy = "project")
    private List<ProjectAssignment> projectAssignment = new ArrayList<>();




}
