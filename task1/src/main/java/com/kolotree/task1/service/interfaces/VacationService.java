package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.Vacation.VacationRequestDto;
import com.kolotree.task1.dto.Vacation.VacationShowDto;
import com.kolotree.task1.exception.NotEnoughVacationDays;
import com.kolotree.task1.model.VacationRequestStatus;

import java.util.List;

public interface VacationService {

    VacationShowDto requestVacation(VacationRequestDto vacationRequestDto) throws NotEnoughVacationDays;

    List<VacationShowDto> myVacations();

    void approveVacation(VacationRequestStatus vacationRequestStatus, Long vacationRequestId);
}
