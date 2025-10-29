package com.kolotree.task1.dto.earnings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MonthlyEarningByProject {
    private Long id;
    private String projectName;
    private int hoursWorked;
    private double monthlyEarnings;
}
