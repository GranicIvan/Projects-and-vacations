package com.kolotree.task1.controller;

import com.kolotree.task1.dto.monthlyLog.AddMonthlyLogDto;
import com.kolotree.task1.dto.monthlyLog.MonthlyLogShowDto;
import com.kolotree.task1.service.interfaces.MonthlyLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/monthlyLog")
public class MonthlyLogController {

    private final MonthlyLogService monthlyLogService;

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/addHoursToProjectForMonth")
    public ResponseEntity<MonthlyLogShowDto> addHoursToProjectForMonth(@RequestBody AddMonthlyLogDto requestBody) {

        MonthlyLogShowDto monthlyLogShowDto = monthlyLogService.addHoursToProjectForMonth(requestBody);

        return ResponseEntity.ok(monthlyLogShowDto);

    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/myMonthlyLogs")
    public ResponseEntity<List<MonthlyLogShowDto>> myMonthlyLogs() {
        List<MonthlyLogShowDto> monthlyLogList = monthlyLogService.myMonthlyLogs();

        return ResponseEntity.ok(monthlyLogList);
    }
}
