package com.kolotree.task1.repository;

import com.kolotree.task1.model.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {

    Optional<ProjectAssignment> findByUserIdAndProjectId(Long userId, Long projectId);

}
