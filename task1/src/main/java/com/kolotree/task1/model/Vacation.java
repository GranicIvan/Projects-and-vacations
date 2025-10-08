package com.kolotree.task1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "vacation")
@Data
public class Vacation {

    @Id
    private long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date startDate;

    private Date endDate;

    private int vacationLength;

    private VacationRequestStatus vacationRequestStatus;


    public Vacation(User user, Date startDate, Date endDate) {
        this.setUser(user);
        this.startDate = endDate;
        this.endDate = endDate;
        this.vacationLength = calculateWorkDaysBetweenDates(startDate, endDate);
    }

    private int calculateWorkDaysBetweenDates(Date startDate, Date endDate) {

        // Ensure start <= end
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        int workDays = 0;

        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            // Skip Saturday (7) and Sunday (1)
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workDays++;
            }
            calendar.add(Calendar.DATE, 1);
        }

        return workDays;
    }
}
