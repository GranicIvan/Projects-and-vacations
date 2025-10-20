package com.kolotree.task1.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectUpdateDto {
    @NotBlank
    private String projectName;
    private String description;

    private boolean activeStatus;
}