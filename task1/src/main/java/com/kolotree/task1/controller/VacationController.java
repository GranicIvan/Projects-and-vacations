package com.kolotree.task1.controller;

import com.kolotree.task1.dto.vacation.SetVacationStatusDto;
import com.kolotree.task1.dto.vacation.VacationRequestDto;
import com.kolotree.task1.dto.vacation.VacationShowDto;
import com.kolotree.task1.dto.vacation.VacationShowWithUserDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<VacationShowDto> getById(@PathVariable Integer id) {
        VacationShowDto vacationShowDto = vacationService.getOne(id);

        if (vacationShowDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vacationShowDto);
    }

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<VacationShowWithUserDto>> getAll() {
        List<VacationShowWithUserDto> vacationRequestList = vacationService.getAll();
        return ResponseEntity.ok(vacationRequestList);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/setVacationRequestStatus")
    public ResponseEntity setVacationRequestStatus(@RequestBody SetVacationStatusDto vacationRequestStatusDto) {

        vacationService.setVacationRequestStatus(vacationRequestStatusDto.getVacationRequestStatus(), vacationRequestStatusDto.getId());

        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAwaitingVacationRequests")
    public ResponseEntity<List<VacationShowWithUserDto>> getAwaitingVacationRequests() {
        List<VacationShowWithUserDto> vacationRequestList = vacationService.getAwaitingVacationRequests();
        return ResponseEntity.ok(vacationRequestList);
    }

}
