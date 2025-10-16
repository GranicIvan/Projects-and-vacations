package com.kolotree.task1.service.interfaces;


import com.kolotree.task1.dto.monthlyLog.AddMonthlyLogDto;
import com.kolotree.task1.model.MonthlyLog;

public interface MonthlyLogService {

    MonthlyLog addHoursToProjectForMonth(AddMonthlyLogDto requestBody);

}
