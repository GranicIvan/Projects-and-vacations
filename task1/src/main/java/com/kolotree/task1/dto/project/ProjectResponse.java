package com.kolotree.task1.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProjectResponse {
    Integer id;
    String projectName;
    String description;
    Double monthlyIncome;

    public ProjectResponse(Integer id, String projectName, String description, Double monthlyIncome) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.monthlyIncome = monthlyIncome;
    }
}
