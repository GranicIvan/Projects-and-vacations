package com.kolotree.task1.dto.user;

import com.kolotree.task1.model.UserType;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsDto implements UserDetails {

    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String address;
    private Integer vacationDaysLeft;

    private UserType userType;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
