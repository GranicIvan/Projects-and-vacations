package com.kolotree.task1.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kolotree.task1.model.id.MonthlyLogId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;


@Entity
@Table(name = "monthly_log")
@Data
public class MonthlyLog {

    @Id
    private MonthlyLogId id;

    private int horusWorked;
}
