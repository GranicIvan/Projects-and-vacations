package com.kolotree.task1.repository;

import com.kolotree.task1.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.activeStatus = :active WHERE u.id = :id")
    void updateActiveStatus(@Param("id") Integer id, @Param("active") boolean active);

    Optional<User> findById(Long id);

    @Modifying
    @Transactional
    @Query("Update User u SET u.vacationDaysLeft = (u.vacationDaysLeft - :useAmount) WHERE u.id= :id")
    void useVacation(@Param("id") Long id, @Param("useAmount") int useAmount);
}
