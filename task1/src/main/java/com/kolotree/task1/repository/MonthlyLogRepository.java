package com.kolotree.task1.repository;

import com.kolotree.task1.model.MonthlyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyLogRepository extends JpaRepository<MonthlyLog, Integer> {
}
