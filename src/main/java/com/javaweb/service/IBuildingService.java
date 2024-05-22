package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);

    int countTotalItems(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO save(BuildingDTO buildingDTO);

    BuildingDTO getBuildingById(Long id);

    void deleteBuildings(List<Long> ids);

    ResponseDTO getStaffs(Long buildingId);

    void assignBuildingToStaffs(AssignmentBuildingDTO assignmentBuildingDTO);
}
