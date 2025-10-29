package com.kolotree.task1.repository;

import com.kolotree.task1.model.MonthlyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyLogRepository extends JpaRepository<MonthlyLog, Integer> {

    List<MonthlyLog> findByProjectAssignment_User_Id(Long userId);


    List<MonthlyLog> findByProjectAssignment_Project_Id(Integer projectId);
}
