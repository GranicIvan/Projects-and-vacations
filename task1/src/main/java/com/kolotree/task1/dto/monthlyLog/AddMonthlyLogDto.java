package com.kolotree.task1.dto.monthlyLog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.YearMonth;

@Data
public class AddMonthlyLogDto {
    private Long projectId;
    private int hoursWorked;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
    private YearMonth yearMonth;
}
