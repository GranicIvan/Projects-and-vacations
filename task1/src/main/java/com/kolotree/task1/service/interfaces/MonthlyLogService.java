package com.kolotree.task1.service.interfaces;


import com.kolotree.task1.dto.monthlyLog.AddMonthlyLogDto;
import com.kolotree.task1.dto.monthlyLog.MonthlyLogShowDto;
import com.kolotree.task1.model.MonthlyLog;

import java.util.List;

public interface MonthlyLogService {

    MonthlyLog addHoursToProjectForMonth(AddMonthlyLogDto requestBody);

    List<MonthlyLogShowDto> myMonthlyLogs();
}
