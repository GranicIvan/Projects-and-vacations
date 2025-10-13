package com.kolotree.task1.dto.project;

import com.kolotree.task1.model.ProjectAssignment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class  ProjectCreateDto {
    private long id;
    @NotBlank
    private String projectName;
    private String description;
    private List<ProjectAssignment> projectAssignment = new ArrayList<>();
}
