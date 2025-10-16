package com.kolotree.task1.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectShowDto {

    private Long id;

    private String projectName;

    private String description;

    private boolean activeStatus;

}
