package com.kolotree.task1.dto.projectAssignment;

import com.kolotree.task1.dto.project.ProjectShowDto;
import com.kolotree.task1.dto.user.UserShowSlimDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjectAssignmentSlimShowDto {

    private Long id;

    private UserShowSlimDto user;

    private ProjectShowDto project;

    private Double hourlyPay;
}
