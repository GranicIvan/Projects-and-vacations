package com.kolotree.task1.dto.user;

import com.kolotree.task1.model.User;
import com.kolotree.task1.model.UserOnProject;
import com.kolotree.task1.model.UserType;
import com.kolotree.task1.model.Vacation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class UserShowDTO {

    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String address;
    private Integer vacationDaysLeft;

    private UserType userType;

    private List<UserOnProject> UserOnProject = new ArrayList<>();
    private List<Vacation> vacations = new ArrayList<>();


}
