package com.kolotree.task1.model;

import com.kolotree.task1.model.id.MonthlyLogId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;


@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_assignment_id", "year_month"})
})
@IdClass(MonthlyLogId.class)
@Data
public class MonthlyLog {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_assignment_id")
    private ProjectAssignment projectAssignment;


    @Id
    private YearMonth yearMonth;

    private int hoursWorked;

}
