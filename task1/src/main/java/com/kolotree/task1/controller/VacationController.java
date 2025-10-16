package com.kolotree.task1.controller;

import com.kolotree.task1.dto.Vacation.VacationRequestDto;
import com.kolotree.task1.dto.Vacation.VacationShowDto;
import com.kolotree.task1.exception.NotEnoughVacationDays;
import com.kolotree.task1.service.interfaces.VacationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vacations")
public class VacationController {

    private final VacationService vacationService;

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/requestVacation")
    public ResponseEntity<VacationShowDto> requestVacation(@Valid @RequestBody VacationRequestDto vacationRequestDto) throws NotEnoughVacationDays {
        VacationShowDto vacationRequest = vacationService.requestVacation(vacationRequestDto);
        return ResponseEntity.ok(vacationRequest);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/myVacations")
    public ResponseEntity<List<VacationShowDto>> myVacations() {
        List<VacationShowDto> vacationRequestList = vacationService.myVacations();
        return ResponseEntity.ok(vacationRequestList);
    }


}
