package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.Vacation.VacationRequestDto;
import com.kolotree.task1.exception.NotEnoughVacationDays;
import com.kolotree.task1.model.VacationRequest;

import java.util.List;

public interface VacationService {

    VacationRequest requestVacation(VacationRequestDto vacationRequestDto) throws NotEnoughVacationDays;

    List<VacationRequest> myVacations();


}
