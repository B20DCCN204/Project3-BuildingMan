package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByBuildingIdIn(List<Long> ids);

    @Query("SELECT ab.staff FROM AssignmentBuildingEntity ab WHERE ab.building.id = :buildingId")
    List<UserEntity> findUsersByBuildingId(@Param("buildingId") Long id);
}
