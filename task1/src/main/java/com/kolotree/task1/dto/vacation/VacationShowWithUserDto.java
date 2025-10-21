package com.kolotree.task1.dto.vacation;

import com.kolotree.task1.dto.user.UserShowSlimDto;
import com.kolotree.task1.model.VacationRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VacationShowWithUserDto {
    private long id;

    private UserShowSlimDto user;

    private Date startDate;

    private Date endDate;

    private int vacationLength;

    private VacationRequestStatus vacationRequestStatus;

}
