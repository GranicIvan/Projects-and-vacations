package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.vacation.VacationRequestDto;
import com.kolotree.task1.dto.vacation.VacationShowDto;
import com.kolotree.task1.dto.vacation.VacationShowWithUserDto;
import com.kolotree.task1.exception.NotEnoughVacationDays;
import com.kolotree.task1.model.VacationRequestStatus;

import java.util.List;

public interface VacationService {

    VacationShowDto requestVacation(VacationRequestDto vacationRequestDto) throws NotEnoughVacationDays;

    List<VacationShowDto> myVacations();

    void setVacationRequestStatus(VacationRequestStatus vacationRequestStatus, Long vacationRequestId);

    List<VacationShowWithUserDto> getAwaitingVacationRequests();

    List<VacationShowWithUserDto> getAll();

    VacationShowDto getOne(Integer id);
}
