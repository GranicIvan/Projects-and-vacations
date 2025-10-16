package com.kolotree.task1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kolotree.task1.model.id.MonthlyLogId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;


@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_assignment_id", "year_month"})
})
@IdClass(MonthlyLogId.class)
@Data
@NoArgsConstructor
public class MonthlyLog {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_assignment_id")
    private ProjectAssignment projectAssignment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
    @Id
    private YearMonth yearMonth;

    private int hoursWorked;

}
