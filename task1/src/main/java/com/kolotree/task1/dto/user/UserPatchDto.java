package com.kolotree.task1.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPatchDto {
    private String firstName;
    private String lastName;
    @Past
    private LocalDate dateOfBirth;
    @Email
    private String email;
    private String address;
    @Min(0)
    private Integer vacationDaysLeft;
}