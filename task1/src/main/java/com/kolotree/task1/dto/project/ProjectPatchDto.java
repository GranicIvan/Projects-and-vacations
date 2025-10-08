package com.kolotree.task1.dto.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectPatchDto {

    private String projectName;
    private String description;
    @Positive(message = "monthlyIncome must be > 0")
    private Double monthlyIncome;

}
