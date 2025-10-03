package com.kolotree.task1.dto.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectPatchDto {

    private String projectName;
    private String description;
    @Positive(message = "monthlyIncome must be > 0")
    private Double monthlyIncome;

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(Double monthlyIncome) { this.monthlyIncome = monthlyIncome; }

}
