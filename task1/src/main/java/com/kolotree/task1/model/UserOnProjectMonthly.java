package com.kolotree.task1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;


@Entity
@Table(name = "user_on_project_monthly")
@Data
public class UserOnProjectMonthly {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_on_project_id")
    private UserOnProject userOnProject;

    @Id
    private YearMonth yearMonth;

    private int horusWorked;
}
