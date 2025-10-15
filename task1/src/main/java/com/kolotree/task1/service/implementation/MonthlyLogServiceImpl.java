package com.kolotree.task1.service.implementation;

import com.kolotree.task1.repository.MonthlyLogRepository;
import com.kolotree.task1.service.interfaces.MonthlyLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MonthlyLogServiceImpl implements MonthlyLogService {

    private final MonthlyLogRepository monthlyLogRepository;
}
