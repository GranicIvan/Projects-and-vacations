package com.kolotree.task1.service.interfaces;


import com.kolotree.task1.dto.earnings.MonthlyEarningByProject;
import com.kolotree.task1.dto.monthlyLog.AddMonthlyLogDto;
import com.kolotree.task1.dto.monthlyLog.MonthlyLogShowDto;

import java.time.YearMonth;
import java.util.List;

public interface MonthlyLogService {

    MonthlyLogShowDto addHoursToProjectForMonth(AddMonthlyLogDto requestBody);

    List<MonthlyLogShowDto> myMonthlyLogs();

    List<MonthlyLogShowDto> forProject(Integer projectId);

    List<MonthlyLogShowDto> getAll();

    List<MonthlyEarningByProject> monthlyLogsForMonthByProjects(YearMonth yearMonth);
}
