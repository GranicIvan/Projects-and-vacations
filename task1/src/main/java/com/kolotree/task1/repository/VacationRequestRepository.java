package com.kolotree.task1.repository;

import com.kolotree.task1.model.User;
import com.kolotree.task1.model.VacationRequest;
import com.kolotree.task1.model.VacationRequestStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Integer> {

    List<VacationRequest> findByUser(User user);

    VacationRequest findById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(" update VacationRequest v set v.vacationRequestStatus = :status where v.id = :id")
    int updateRequestStatus(@Param("status") VacationRequestStatus status, @Param("id") Long vacationRequestId);


    List<VacationRequest> findByVacationRequestStatus(VacationRequestStatus vacationRequestStatus);
}