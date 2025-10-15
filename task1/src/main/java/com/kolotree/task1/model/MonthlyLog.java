package com.kolotree.task1.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;


@Entity
@Table(name = "monthly_log")
@Data
public class MonthlyLog {

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @Id
    @ManyToOne
    @JoinColumn(name = "project_assignment_id")
    private ProjectAssignment projectAssignment;

    @Id
    private YearMonth yearMonth;

    private int horusWorked;
}
