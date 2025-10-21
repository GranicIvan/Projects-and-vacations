package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.vacation.VacationShowDto;
import com.kolotree.task1.model.VacationRequest;

import java.util.List;

public class VacationMapper {


    public static VacationShowDto toShowDto(VacationRequest vacationRequest) {
        return new VacationShowDto(
                vacationRequest.getId(),
                vacationRequest.getStartDate(),
                vacationRequest.getEndDate(),
                vacationRequest.getVacationLength(),
                vacationRequest.getVacationRequestStatus()
        );
    }

    public static List<VacationShowDto> toShowDtoList(List<VacationRequest> vacations) {
        return vacations.stream()
                .map(VacationMapper::toShowDto)
                .toList();
    }
}
