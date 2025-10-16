package com.kolotree.task1.dto.monthlyLog;

import com.kolotree.task1.dto.projectAssignment.ProjectAssignmentSlimShowDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.YearMonth;

@AllArgsConstructor
@Data
public class MonthlyLogShowDto {

    private ProjectAssignmentSlimShowDto projectAssignment;

    private YearMonth yearMonth;

    private int hoursWorked;
}
