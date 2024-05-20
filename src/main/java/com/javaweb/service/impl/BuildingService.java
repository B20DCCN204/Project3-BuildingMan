package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest, pageable);
        List<BuildingSearchResponse> results = new ArrayList<>();
        for(BuildingEntity building : buildingEntities){
            BuildingSearchResponse buildingSearchResponse = buildingConverter.toBuildingSearchResponse(building);
            results.add(buildingSearchResponse);
        }
        return results;
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItem(buildingSearchRequest);
    }

    // Add or update;
    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO buildingDTO) {
        Long buildingId = buildingDTO.getId();

        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        //Update
        if(buildingId != null){
            BuildingEntity found = buildingRepository.findById(buildingId)
                    .orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setId(buildingId);
            buildingEntity.setAvatar(found.getAvatar());
            rentAreaRepository.deleteAll(found.getRentAreaEntities());
        }

        BuildingEntity savedBuilding = buildingRepository.save(buildingEntity);

        List<RentAreaEntity> rentAreaEntities = rentAreaConverter.toListRentAreaEntity(buildingDTO.getRentArea(), savedBuilding);
        rentAreaRepository.saveAll(rentAreaEntities);

        savedBuilding.setRentAreaEntities(rentAreaEntities);
        buildingRepository.save(savedBuilding);

        saveThumbnail(buildingDTO, buildingEntity);
        return buildingConverter.toBuilingDTO(savedBuilding);
    }

    @Override
    public BuildingDTO getBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.getOne(id);
        return buildingConverter.toBuilingDTO(buildingEntity);
    }

    @Override
    @Modifying
    @Transactional
    public void deleteBuildings(List<Long> ids) {
        rentAreaRepository.deleteByBuildingIdIn(ids);
        assignmentBuildingRepository.deleteByBuildingIdIn(ids);
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public ResponseDTO getStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.getOne(buildingId);
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = assignmentBuildingRepository.findUsersByBuildingId(buildingId);
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO result = new ResponseDTO();
        for(UserEntity u : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(u.getId());
            staffResponseDTO.setFullName(u.getFullName());
            if(staffAssignment.contains(u)){
                staffResponseDTO.setChecked("checked");
            }else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        result.setData(staffResponseDTOS);
        result.setMessage("success");
        return result;
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
                    File file = new File("D://Program Files/toeic/JavaBackend/Document/RepoImage" + buildingEntity.getAvatar());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }
}
