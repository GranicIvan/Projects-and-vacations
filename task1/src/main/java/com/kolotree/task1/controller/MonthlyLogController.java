package com.kolotree.task1.controller;

import com.kolotree.task1.dto.monthlyLog.AddMonthlyLogDto;
import com.kolotree.task1.model.MonthlyLog;
import com.kolotree.task1.service.interfaces.MonthlyLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/monthlyLog")
public class MonthlyLogController {

    private final MonthlyLogService monthlyLogService;


    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/addHoursToProjectForMonth")
    public ResponseEntity<MonthlyLog> addHoursToProjectForMonth(@RequestBody AddMonthlyLogDto requestBody) {

        MonthlyLog monthlyLog = monthlyLogService.addHoursToProjectForMonth(requestBody);

        return ResponseEntity.ok(monthlyLog);

    }

}
