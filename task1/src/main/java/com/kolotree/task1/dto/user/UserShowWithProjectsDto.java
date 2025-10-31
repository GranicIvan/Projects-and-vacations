package com.kolotree.task1.dto.user;

import com.kolotree.task1.dto.projectAssignment.ProjectAssignmentRequestBodyDto;
import com.kolotree.task1.dto.projectAssignment.ProjectAssignmentSlimShowDto;
import com.kolotree.task1.model.ProjectAssignment;
import com.kolotree.task1.model.UserRole;
import com.kolotree.task1.model.VacationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserShowWithProjectsDto {

    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String address;
    private Integer vacationDaysLeft;

    private UserRole userRole;

    private List<ProjectAssignmentSlimShowDto> ProjectAssignment = new ArrayList<>();
    private List<VacationRequest> vacationRequests = new ArrayList<>();
}
