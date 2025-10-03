package com.kolotree.task1.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectCreateDto {
    @NotBlank
    private String projectName;
    private String description;
    @NotNull
    private Double monthlyIncome;
}
