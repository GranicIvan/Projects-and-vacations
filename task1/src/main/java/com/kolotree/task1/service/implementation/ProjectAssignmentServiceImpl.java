package com.kolotree.task1.service.implementation;

import com.kolotree.task1.model.Project;
import com.kolotree.task1.model.ProjectAssignment;
import com.kolotree.task1.model.User;
import com.kolotree.task1.repository.ProjectAssignmentRepository;
import com.kolotree.task1.service.interfaces.ProjectAssignmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectAssignmentServiceImpl implements ProjectAssignmentService {

    private final ProjectAssignmentRepository projectAssignmentRepository;

    @Override
    public void addEmployeeToProject(Long userId, Long projectId, double hourlyRate) {
        ProjectAssignment projectAssignment = ProjectAssignment.builder()
                .user(User.builder().id(userId).build())
                .project(Project.builder().id(projectId).build())
                .hourly_pay(hourlyRate)
                .build();

        projectAssignmentRepository.save(projectAssignment);
    }

    @Override
    public ProjectAssignment getByUserIdAndProjectId(Long userId, Long projectId) {
        Optional<ProjectAssignment> optionalProjectAssignment = projectAssignmentRepository.findByUserIdAndProjectId(userId, projectId);
        if (optionalProjectAssignment.isEmpty()) {
            throw new EntityNotFoundException("Entity ProjectAssignment with userID:" + userId + ", and projectId:" + projectId + " NOT FOUND");
        }
        return optionalProjectAssignment.get();
    }
}
