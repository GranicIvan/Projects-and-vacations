package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.monthlyLog.AddMonthlyLogDto;
import com.kolotree.task1.model.MonthlyLog;
import com.kolotree.task1.model.ProjectAssignment;
import com.kolotree.task1.model.User;
import com.kolotree.task1.repository.MonthlyLogRepository;
import com.kolotree.task1.service.interfaces.MonthlyLogService;
import com.kolotree.task1.service.interfaces.ProjectAssignmentService;
import com.kolotree.task1.service.interfaces.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@AllArgsConstructor
@Service
public class MonthlyLogServiceImpl implements MonthlyLogService {

    private final MonthlyLogRepository monthlyLogRepository;
    private final ProjectAssignmentService projectAssignmentService;
    private final UserService userService;

    private final EntityManager entityManager;

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
}
