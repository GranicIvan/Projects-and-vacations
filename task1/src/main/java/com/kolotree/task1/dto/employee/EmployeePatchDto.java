package com.kolotree.task1.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kolotree.task1.model.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePatchDto {
    private String firstName;          // if provided, must not be blank (checked in controller)
    private String lastName;           // same
    @Past
    private LocalDate dateOfBirth;     // validated only if provided
    @Email
    private String email;              // validated only if provided
    private String address;            // explicit null allowed if you want to clear it
    @Min(0)
    private Integer vacationDaysLeft;  // validated only if provided
    private UserType userType;         // validated only if provided
}