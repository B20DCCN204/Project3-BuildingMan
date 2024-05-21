package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    @Transactional
    public void assignBuildingToStaffs(AssignmentBuildingDTO assignmentBuildingDTO) {
        Long buildingId = assignmentBuildingDTO.getBuildingId();
        List<Long> staffIds = assignmentBuildingDTO.getStaffs();

        BuildingEntity building = buildingRepository.findById(buildingId).orElseThrow(
                () -> new RuntimeException("Building not found!")
        );

        List<UserEntity> alreadyAssignedStaffs = assignmentBuildingRepository.findUsersByBuildingId(buildingId);

        Set<Long> alreadyAssignedStaffIds = alreadyAssignedStaffs.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toSet());

        List<UserEntity> staffsToAssign = userRepository.findAllById(staffIds).stream()
                .filter(userEntity -> !alreadyAssignedStaffIds.contains(userEntity.getId()))
                .collect(Collectors.toList());

        List<Long> staffIdsToUnassign = alreadyAssignedStaffIds.stream()
                .filter(id -> !staffIds.contains(id))
                .collect(Collectors.toList());

        if(!staffIdsToUnassign.isEmpty()){
            assignmentBuildingRepository.deleteByBuildingIdAndStaffIdIn(buildingId, staffIdsToUnassign);
        }

        List<AssignmentBuildingEntity> assignmentBuildingEntities = staffsToAssign.stream()
                .map(userEntity -> new AssignmentBuildingEntity().setBuilding(building).setUser(userEntity))
                .collect(Collectors.toList());

        assignmentBuildingRepository.saveAll(assignmentBuildingEntities);
    }
}
