package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.dto.project.ProjectResponse;
import com.kolotree.task1.model.Project;

public class ProjectMapper {

    private ProjectMapper() {}

    public static void applyPatch(Project target, ProjectPatchDto dto) {
        if (dto.getProjectName() != null) {
            target.setProjectName(dto.getProjectName());
        }
        if (dto.getDescription() != null) {
            target.setDescription(dto.getDescription());
        }
        if (dto.getMonthlyIncome() != null) {
            target.setMonthlyIncome(dto.getMonthlyIncome());
        }
    }

    public static ProjectResponse toResponse(Project p) {
        return new ProjectResponse(
                p.getId(),
                p.getProjectName(),
                p.getDescription(),
                p.getMonthlyIncome()
        );
    }

}
