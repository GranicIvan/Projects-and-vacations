package com.kolotree.task1.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectResponseDto {
    Long id;
    String projectName;
    String description;
    private boolean activeStatus;
}
