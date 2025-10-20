package com.kolotree.task1.dto.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kolotree.task1.model.ProjectAssignment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectPatchDto {

    private String projectName;
    private String description;
    private boolean activeStatus;
    private List<ProjectAssignment> projectAssignment = new ArrayList<>();

}
