package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {

    @Modifying
    @Transactional
    void deleteByBuildingIdIn(List<Long> ids);

    @Modifying
    @Transactional
    @Query("DELETE FROM AssignmentBuildingEntity  ab WHERE ab.building.id = :buildingId AND ab.staff.id IN :staffIds")
    void deleteByBuildingIdAndStaffIdIn(@Param("buildingId") Long buildingId, @Param("staffIds") List<Long> staffIds);

    @Query("SELECT ab.staff FROM AssignmentBuildingEntity ab WHERE ab.building.id = :buildingId")
    List<UserEntity> findUsersByBuildingId(@Param("buildingId") Long id);
}
