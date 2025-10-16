package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.project.ProjectCreateDto;
import com.kolotree.task1.dto.project.ProjectPatchDto;
import com.kolotree.task1.dto.project.ProjectResponseDto;
import com.kolotree.task1.model.Project;

public class ProjectMapper {

    private ProjectMapper() {
    }

    public static void applyPatch(Project target, ProjectPatchDto dto) {
        if (dto.getProjectName() != null) {
            target.setProjectName(dto.getProjectName());
        }
        if (dto.getDescription() != null) {
            target.setDescription(dto.getDescription());
        }
        if (dto.getProjectAssignment() != null) {
            target.setProjectAssignment(dto.getProjectAssignment());
        }


    }

    public static ProjectResponseDto toResponse(Project p) {
        return new ProjectResponseDto(
                p.getId(),
                p.getProjectName(),
                p.getDescription(),
                p.isActiveStatus()
        );
    }

    public static Project createDtoToProject(ProjectCreateDto dto) {

        return Project
                .builder()
                .projectName(dto.getProjectName())
                .description(dto.getDescription())
                .activeStatus(true)
                .build();
    }
}
