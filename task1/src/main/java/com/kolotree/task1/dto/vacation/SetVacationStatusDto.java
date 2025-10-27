package com.kolotree.task1.dto.vacation;

import com.kolotree.task1.model.VacationRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetVacationStatusDto {
    private Long id;
    private VacationRequestStatus vacationRequestStatus;

}
