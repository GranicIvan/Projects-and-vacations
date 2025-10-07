package com.kolotree.task1.model.id;


import com.kolotree.task1.model.Project;
import com.kolotree.task1.model.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.YearMonth;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProjectMonthlyId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private YearMonth month;
}
