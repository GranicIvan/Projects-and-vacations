package com.kolotree.task1.model.id;

import com.kolotree.task1.model.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;
import java.util.Date;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationId {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date start;
}
