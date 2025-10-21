package com.kolotree.task1.dto.vacation;

import lombok.Data;

import java.util.Date;

@Data
public class VacationRequestDto {

    private Date startDate;

    private Date endDate;

}
