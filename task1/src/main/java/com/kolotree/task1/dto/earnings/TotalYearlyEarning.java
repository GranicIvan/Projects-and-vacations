package com.kolotree.task1.dto.earnings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TotalYearlyEarning {
    private YearMonth yearMonth;
    private int hoursWorked;
    private double monthlyEarnings;
}
