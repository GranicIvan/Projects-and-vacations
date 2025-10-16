package com.kolotree.task1.dto.Vacation;

import com.kolotree.task1.model.VacationRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VacationShowDto {

    private long id;

    private Date startDate;

    private Date endDate;

    private int vacationLength;

    private VacationRequestStatus vacationRequestStatus;

}
