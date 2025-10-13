package com.kolotree.task1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;


@Entity
@Table(name = "monthly_log")
@Data
public class MonthlyLog {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_assignment_id")
    private ProjectAssignment projectAssignment;

    @Id
    private YearMonth yearMonth;

    private int horusWorked;
}
