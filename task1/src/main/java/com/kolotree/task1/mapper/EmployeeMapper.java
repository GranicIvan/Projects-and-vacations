package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.employee.EmployeePatchDto;
import com.kolotree.task1.dto.employee.EmployeeUpdateDto;
import com.kolotree.task1.model.Employee;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeMapper {

    public static void applyUpdate(Employee target, EmployeeUpdateDto dto) {
        target.setFirstName(dto.getFirstName());
        target.setLastName(dto.getLastName());
        target.setDateOfBirth(dto.getDateOfBirth());
        target.setEmail(dto.getEmail());
        target.setAddress(dto.getAddress());
        target.setVacationDaysLeft(dto.getVacationDaysLeft());
        target.setUserType(dto.getUserType());
    }

    public static void applyPatch(Employee target, EmployeePatchDto dto) {
        if (dto.getFirstName() != null)        target.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null)         target.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null)      target.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getEmail() != null)            target.setEmail(dto.getEmail());
        if (dto.getAddress() != null)          target.setAddress(dto.getAddress());
        if (dto.getVacationDaysLeft() != null) target.setVacationDaysLeft(dto.getVacationDaysLeft());
        if (dto.getUserType() != null)         target.setUserType(dto.getUserType());
    }

}
