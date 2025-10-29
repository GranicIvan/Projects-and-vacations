package com.kolotree.task1.repository;

import com.kolotree.task1.dto.earnings.MonthlyEarningByProject;
import com.kolotree.task1.model.MonthlyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface MonthlyLogRepository extends JpaRepository<MonthlyLog, Integer> {

    List<MonthlyLog> findByProjectAssignment_User_Id(Long userId);


    List<MonthlyLog> findByProjectAssignment_Project_Id(Integer projectId);


    //    @Query("""
//                SELECT
//                    p.id AS id,
//                    p.projectName AS projectName,
//                    SUM(ml.hoursWorked) AS hoursWorked,
//                    SUM(ml.hoursWorked * pa.hourlyRate) AS monthlyEarnings
//                FROM MonthlyLog ml
//                JOIN ml.projectAssignment pa
//                JOIN pa.project p
//                WHERE ml.yearMonth = :yearMonth
//                GROUP BY p.id, p.projectName
//            """)
   /* @Query("""
                SELECT 
                    p.id,
                    p.projectName,
                    SUM(ml.hoursWorked),
                    SUM(ml.hoursWorked * pa.hourlyPay)

                FROM MonthlyLog ml
                JOIN ml.projectAssignment pa
                JOIN pa.project p
                WHERE ml.yearMonth = :yearMonth
                GROUP BY p.id, p.projectName
            """) */
    @Query("""
                SELECT new com.kolotree.task1.dto.earnings.MonthlyEarningByProject(
                    p.id,
                    p.projectName,
                    CAST(SUM(ml.hoursWorked) AS int),
                    SUM(ml.hoursWorked * pa.hourlyPay)
                )
                FROM MonthlyLog ml
                JOIN ml.projectAssignment pa
                JOIN pa.project p
                WHERE ml.yearMonth = :yearMonth
                GROUP BY p.id, p.projectName
            """)
    List<MonthlyEarningByProject> monthlyEarningGroupedByProjects(@Param("yearMonth") YearMonth yearMonth);
}
