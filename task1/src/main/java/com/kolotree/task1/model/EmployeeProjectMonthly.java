package com.kolotree.task1.model;

import com.kolotree.task1.model.id.EmployeeProjectMonthlyId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;

@Entity
@Table(name = "employee_project_monthly")
@Data
public class EmployeeProjectMonthly {


    @EmbeddedId
    private EmployeeProjectMonthlyId id;

    private int hoursWorkedOnProject; //hoursWorkedOnProjectForMonth

//    private double hourlyRate; //TODO Move this to new table


}
