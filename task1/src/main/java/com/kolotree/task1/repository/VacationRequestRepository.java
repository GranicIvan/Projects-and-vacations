package com.kolotree.task1.repository;

import com.kolotree.task1.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Integer> {


}