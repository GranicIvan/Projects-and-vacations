package com.kolotree.task1.service.implementation;

import com.kolotree.task1.converter.YearMonthAttributeConverter;
import com.kolotree.task1.dto.earnings.EarningsByEmployee;
import com.kolotree.task1.dto.earnings.MonthlyEarningByProject;
import com.kolotree.task1.dto.earnings.TotalYearlyEarning;
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

import java.time.Month;
import java.time.Year;
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
    public MonthlyLogShowDto addHoursToProjectForMonth(AddMonthlyLogDto requestBody) {
        User user = userService.getCurrentUser();
        Long projectId = requestBody.getProjectId();
        int hoursWorked = requestBody.getHoursWorked();

        ProjectAssignment projectAssignment = projectAssignmentService.getByUserIdAndProjectId(user.getId(), projectId);


        YearMonth yearMonth = requestBody.getYearMonth();
        MonthlyLog monthlyLog = new MonthlyLog(projectAssignment, yearMonth, hoursWorked);

        monthlyLog = monthlyLogRepository.save(monthlyLog);

        return MonthlyLogMapper.toShowDto(monthlyLog);

    }

    @Override
    public List<MonthlyLogShowDto> myMonthlyLogs() {
        User user = userService.getCurrentUser();
        List<MonthlyLog> monthlyLogList = monthlyLogRepository.findByProjectAssignment_User_Id(user.getId());
        List<MonthlyLogShowDto> monthlyLogShowDtos = MonthlyLogMapper.toShowDtoList(monthlyLogList);
        return monthlyLogShowDtos;
    }

    @Override
    public List<MonthlyLogShowDto> forProject(Integer projectId) {

        List<MonthlyLog> monthlyLogList = monthlyLogRepository.findByProjectAssignment_Project_Id(projectId);

        List<MonthlyLogShowDto> monthlyLogShowDtos = MonthlyLogMapper.toShowDtoList(monthlyLogList);
        return monthlyLogShowDtos;
    }

    @Override
    public List<MonthlyLogShowDto> getAll() {
        List<MonthlyLog> monthlyLogList = monthlyLogRepository.findAll();

        return MonthlyLogMapper.toShowDtoList(monthlyLogList);
    }

    @Override
    public List<MonthlyEarningByProject> monthlyLogsForMonthByProjects(YearMonth yearMonth) {

        List<MonthlyEarningByProject> monthlyEarningByProjects = monthlyLogRepository.monthlyEarningGroupedByProjects(yearMonth);

        return monthlyEarningByProjects;
    }

    @Override
    public List<EarningsByEmployee> yearlyLogsForMonthByProjects(YearMonth yearMonth) {
        List<EarningsByEmployee> earningsByEmployees = monthlyLogRepository.yearlyLogsForMonthByEmployee(yearMonth);
        return earningsByEmployees;
    }



    @Override
    public List<MonthlyEarningByProject> yearlyLogsForYearByProjects(int year) {
        YearMonth yearMonthStart = YearMonth.of(year, Month.JANUARY);
        YearMonth yearMonthEnd = YearMonth.of(year, Month.DECEMBER);
        List<MonthlyEarningByProject> monthlyEarningByProjects = monthlyLogRepository.yearlyEarningGroupedByProjects(yearMonthStart, yearMonthEnd);

        return monthlyEarningByProjects;
    }

    @Override
    public List<EarningsByEmployee> yearlyLogsForYearByEmployee(int year) {
        YearMonth yearMonthStart = YearMonth.of(year, Month.JANUARY);
        YearMonth yearMonthEnd = YearMonth.of(year, Month.DECEMBER);
        List<EarningsByEmployee> earningsByEmployees = monthlyLogRepository.yearlyLogsForMonthByEmployee(yearMonthStart, yearMonthEnd);

        return earningsByEmployees;
    }

    @Override
    public List<TotalYearlyEarning> totalYearlyEarnings(int startYear, int endYear) {
        YearMonth yearMonthStart = YearMonth.of(startYear, Month.JANUARY);
        YearMonth yearMonthEnd = YearMonth.of(endYear, Month.DECEMBER);

        List<TotalYearlyEarning> totalYearlyEarnings = monthlyLogRepository.totalYearlyEarnings(yearMonthStart, yearMonthEnd);

        return totalYearlyEarnings;
    }

    @Override
    public List<MonthlyLogShowDto> reportForUserForMonth(int userId, YearMonth yearMonth) {
        List<MonthlyLog> monthlyLogList = monthlyLogRepository.findByProjectAssignment_User_IdAndYearMonth(userId, yearMonth);


        return MonthlyLogMapper.toShowDtoList(monthlyLogList);
    }


}
