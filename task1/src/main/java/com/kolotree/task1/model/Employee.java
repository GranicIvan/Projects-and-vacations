package com.kolotree.task1.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String salt;
    private String address;
    private Integer vacationDaysLeft;
    private UserType userType;

    private Employee() {}


    public Employee(Integer id, String firstName, String lastName, LocalDate dateOfBirth, String email, String password, String salt, String address, Integer vacationDaysLeft, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.address = address;
        this.vacationDaysLeft = vacationDaysLeft;
        this.userType = userType;
    }
}
