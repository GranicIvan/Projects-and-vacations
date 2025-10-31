package com.kolotree.task1.dto.projectAssignment;

import com.kolotree.task1.dto.project.ProjectShowDto;
import com.kolotree.task1.dto.user.UserShowSlimDto;
import lombok.Data;

@Data
public class ProjectAssignmentNoUserShowDto {

    private Long id;

    private ProjectShowDto project;

    private Double hourlyPay;
}
