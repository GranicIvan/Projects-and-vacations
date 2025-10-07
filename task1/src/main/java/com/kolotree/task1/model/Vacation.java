package com.kolotree.task1.model;

import com.kolotree.task1.model.id.VacationId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;
import java.util.Date;

@Entity
@Table(name = "vacation")
@Data
public class Vacation {

    @Id
    private VacationId vacationId;

    private Date endDate;

    private int vacationLength;

    public Vacation(User user, Date startDate, Date endDate) {
        this.vacationId.setUser(user);
        this.vacationId.setStart(startDate);
        this.endDate = endDate;
        this.vacationLength = calculateWorkDaysBetweenDates(startDate, endDate);
    }

    private int calculateWorkDaysBetweenDates(Date start, Date end) {
        return 0; // TODO Add method
    }
}
