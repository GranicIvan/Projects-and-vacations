package com.kolotree.task1.dto.projectAssignment;

import lombok.Data;

@Data
public class ProjectAssignmentRequestBodyDto {
    private Long userId;
    private Long projectId;
    private double hourlyRate;
}
