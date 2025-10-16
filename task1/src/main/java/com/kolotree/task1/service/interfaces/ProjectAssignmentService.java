package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.model.ProjectAssignment;

public interface ProjectAssignmentService {

    void addEmployeeToProject(Long userId, Long projectId, double hourlyRate);

    ProjectAssignment getByUserIdAndProjectId(Long userId, Long projectId);
}
