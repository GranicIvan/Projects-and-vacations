package com.kolotree.task1.controller;

import com.kolotree.task1.model.MonthlyLog;
import com.kolotree.task1.service.interfaces.MonthlyLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/monthlyLog")
public class MonthlyLogController {

    private final MonthlyLogService monthlyLogService;
}
