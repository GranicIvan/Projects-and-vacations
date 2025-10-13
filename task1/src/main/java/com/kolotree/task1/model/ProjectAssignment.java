package com.kolotree.task1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "project_assignment")
@Data
public class ProjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private Double hourly_pay;
}
