package com.kolotree.task1.repository;

import com.kolotree.task1.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Modifying
    @Query("UPDATE User u SET u.activeStatus = :active WHERE u.id = :id")
    void updateActiveStatus(Integer id, boolean b);

    List<Project> findAllByProjectAssignment_User_Id(int userId);
}
