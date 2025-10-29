package com.kolotree.task1.repository;

import com.kolotree.task1.dto.earnings.EarningsByEmployee;
import com.kolotree.task1.dto.earnings.MonthlyEarningByProject;
import com.kolotree.task1.dto.earnings.TotalYearlyEarning;
import com.kolotree.task1.model.MonthlyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface MonthlyLogRepository extends JpaRepository<MonthlyLog, Integer> {

    List<MonthlyLog> findByProjectAssignment_User_Id(Long userId);


    List<MonthlyLog> findByProjectAssignment_Project_Id(Integer projectId);

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
                WHERE ml.yearMonth BETWEEN :startMonth AND :endMonth
                GROUP BY p.id, p.projectName
            """)
    List<MonthlyEarningByProject> yearlyEarningGroupedByProjects(
            @Param("startMonth") YearMonth startMonth,
            @Param("endMonth") YearMonth endMonth
    );

    @Query("""
                SELECT new com.kolotree.task1.dto.earnings.EarningsByEmployee(
                    u.id,
                    u.firstName,
                    u.lastName,
                    u.email,
                    CAST(SUM(ml.hoursWorked) AS int),
                    SUM(ml.hoursWorked * pa.hourlyPay)
                )
                FROM MonthlyLog ml
                JOIN ml.projectAssignment pa
                JOIN pa.user u
                WHERE ml.yearMonth = :yearMonth
                GROUP BY u.id, u.firstName, u.lastName, u.email
            """)
    List<EarningsByEmployee> yearlyLogsForMonthByEmployee(@Param("yearMonth") YearMonth yearMonth);


    @Query("""
                SELECT new com.kolotree.task1.dto.earnings.EarningsByEmployee(
                    u.id,
                    u.firstName,
                    u.lastName,
                    u.email,
                    CAST(SUM(ml.hoursWorked) AS int),
                    SUM(ml.hoursWorked * pa.hourlyPay)
                )
                FROM MonthlyLog ml
                JOIN ml.projectAssignment pa
                JOIN pa.user u
                WHERE ml.yearMonth BETWEEN :startMonth AND :endMonth
                GROUP BY u.id, u.firstName, u.lastName, u.email
            """)
    List<EarningsByEmployee> yearlyLogsForMonthByEmployee(@Param("startMonth") YearMonth startMonth,
                                                          @Param("endMonth") YearMonth endMonth);


    @Query("""
            SELECT new com.kolotree.task1.dto.earnings.TotalYearlyEarning(
                ml.yearMonth,
                CAST(SUM(ml.hoursWorked) AS int),
                SUM(ml.hoursWorked * pa.hourlyPay)
            )
            FROM MonthlyLog ml
            JOIN ml.projectAssignment pa
            WHERE ml.yearMonth BETWEEN :startYear AND :endYear
            GROUP BY ml.yearMonth
            """)
    List<TotalYearlyEarning> totalYearlyEarnings(@Param("startYear") YearMonth yearMonthStart, @Param("endYear") YearMonth yearMonthEnd);
}
