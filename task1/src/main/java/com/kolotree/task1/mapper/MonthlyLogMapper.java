package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.monthlyLog.MonthlyLogShowDto;
import com.kolotree.task1.model.MonthlyLog;

import java.util.List;

public class MonthlyLogMapper {


    public static MonthlyLogShowDto toShowDto(MonthlyLog monthlyLog) {

        return new MonthlyLogShowDto(
                ProjectAssignmentMapper.toSlimShowDto(monthlyLog.getProjectAssignment()),
                monthlyLog.getYearMonth(),
                monthlyLog.getHoursWorked()
        );
    }

    public static List<MonthlyLogShowDto> toShowDtoList(List<MonthlyLog> monthlyLogs) {
        return monthlyLogs.stream()
                .map(MonthlyLogMapper::toShowDto)
                .toList();
    }

}
