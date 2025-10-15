package com.kolotree.task1.model.id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kolotree.task1.model.ProjectAssignment;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.YearMonth;

public class MonthlyLogId {

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @Id
    @ManyToOne
    @JoinColumn(name = "project_assignment_id")
    private ProjectAssignment projectAssignment;

    @Id
    private YearMonth yearMonth;
}
