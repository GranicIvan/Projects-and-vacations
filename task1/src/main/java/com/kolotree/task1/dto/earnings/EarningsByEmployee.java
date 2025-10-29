package com.kolotree.task1.dto.earnings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EarningsByEmployee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int hoursWorked;
    private double monthlyEarnings;
}
