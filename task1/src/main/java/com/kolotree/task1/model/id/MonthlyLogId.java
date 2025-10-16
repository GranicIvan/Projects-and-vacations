package com.kolotree.task1.model.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.YearMonth;

@AllArgsConstructor
@NoArgsConstructor
public class MonthlyLogId implements Serializable {

    private Long projectAssignment;

    private YearMonth yearMonth;
}
