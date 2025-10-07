package com.kolotree.task1.model;

import com.kolotree.task1.model.id.EmployeeProjectMonthlyId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee_project_monthly")
@Data
public class EmployeeProjectMonthly {


    @EmbeddedId
    private EmployeeProjectMonthlyId id;

    private int hoursWorkedOnProject; //hoursWorkedOnProjectForMonth

    private double hourlyRate;


}
