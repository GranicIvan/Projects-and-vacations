package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.projectAssignment.ProjectAssignmentSlimShowDto;
import com.kolotree.task1.model.ProjectAssignment;

public class ProjectAssignmentMapper {

    public static ProjectAssignmentSlimShowDto toSlimShowDto(ProjectAssignment projectAssignment) {
        return new ProjectAssignmentSlimShowDto(
                projectAssignment.getId(),
                UserMapper.toShowSlimDto(projectAssignment.getUser()),
                ProjectMapper.toShowDto(projectAssignment.getProject()),
                projectAssignment.getHourlyPay()
        );
    }
}
