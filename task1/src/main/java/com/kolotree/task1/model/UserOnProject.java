package com.kolotree.task1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_on_project")
@Data
public class UserOnProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO da li je osigurano da jedan user ne moze biti dva puta na jednom projektu
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private Double hourly_pay;
}
