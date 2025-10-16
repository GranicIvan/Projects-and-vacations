package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.monthlyLog.AddMonthlyLogDto;
import com.kolotree.task1.dto.monthlyLog.MonthlyLogShowDto;
import com.kolotree.task1.mapper.MonthlyLogMapper;
import com.kolotree.task1.model.MonthlyLog;
import com.kolotree.task1.model.ProjectAssignment;
import com.kolotree.task1.model.User;
import com.kolotree.task1.repository.MonthlyLogRepository;
import com.kolotree.task1.service.interfaces.MonthlyLogService;
import com.kolotree.task1.service.interfaces.ProjectAssignmentService;
import com.kolotree.task1.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@AllArgsConstructor
@Service
public class MonthlyLogServiceImpl implements MonthlyLogService {

    private final MonthlyLogRepository monthlyLogRepository;
    private final ProjectAssignmentService projectAssignmentService;
    private final UserService userService;


    @Override
    @Transactional
    public MonthlyLog addHoursToProjectForMonth(AddMonthlyLogDto requestBody) {
        User user = userService.getCurrentUser();
        Long projectId = requestBody.getProjectId();
        int hoursWorked = requestBody.getHoursWorked();

        ProjectAssignment projectAssignment = projectAssignmentService.getByUserIdAndProjectId(user.getId(), projectId);


        YearMonth yearMonth = requestBody.getYearMonth();
        MonthlyLog monthlyLog = new MonthlyLog(projectAssignment, yearMonth, hoursWorked);

        return monthlyLogRepository.save(monthlyLog); //TODO make DTO for this

    }

    @Override
    public List<MonthlyLogShowDto> myMonthlyLogs() {
        User user = userService.getCurrentUser();
        List<MonthlyLog> monthlyLogList = monthlyLogRepository.findByProjectAssignment_User_Id(user.getId());
        List<MonthlyLogShowDto> monthlyLogShowDtos = MonthlyLogMapper.toShowDtoList(monthlyLogList);
        return monthlyLogShowDtos;
    }
}
