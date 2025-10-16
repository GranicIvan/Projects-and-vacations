package com.kolotree.task1.controller;

import com.kolotree.task1.dto.projectAssignment.ProjectAssignmentRequestBodyDto;
import com.kolotree.task1.service.interfaces.ProjectAssignmentService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/projectAssignments")
public class ProjectAssignmentController {

    ProjectAssignmentService projectAssignmentService;

    private static final Logger logger = LogManager.getLogger(ProjectAssignmentController.class);


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addEmployeeToProject")
//    public ResponseEntity addEmployeeToProject(@RequestBody Long userId, @RequestBody Long projectId, @RequestBody double hourlyRate){
    public ResponseEntity addEmployeeToProject(@RequestBody ProjectAssignmentRequestBodyDto requestBody) {
        long userId = requestBody.getUserId();
        long projectId = requestBody.getProjectId();
        double hourlyRate = requestBody.getHourlyRate();

        logger.warn("UserId:" + userId + ", projectId:" + projectId + " hourlyRate:" + hourlyRate);
        projectAssignmentService.addEmployeeToProject(userId, projectId, hourlyRate);
        return ResponseEntity.ok("Successfully added Employee with id: " + userId + " to project with id: " + projectId);
    }


}
