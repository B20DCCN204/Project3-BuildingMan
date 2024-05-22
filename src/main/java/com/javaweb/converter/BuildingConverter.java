package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity building){
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(building, BuildingSearchResponse.class);

        buildingSearchResponse.setAddress(building.getStreet()+", "+building.getWard()+", " +
                (building.getDistrict() != null ? districtCode.valueOf(building.getDistrict()).getDistrictName() : ""));

        List<RentAreaEntity> rentAreaEntityList = building.getRentAreaEntities();
        String rentAreas = rentAreaEntityList.stream().map(ra -> String.valueOf(ra.getValue())).collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentAreas);

        return buildingSearchResponse;
    }

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setTypeCode(
                String.join(",", buildingDTO.getTypeCode())
        );
        buildingEntity.setRentAreaEntities(rentAreaConverter.toListRentAreaEntity(buildingDTO.getRentArea(), buildingEntity));
        return buildingEntity;
    }

    public BuildingDTO toBuilingDTO(BuildingEntity building){
        BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);
        buildingDTO.setTypeCode(
                new ArrayList<>(Arrays.asList(building.getTypeCode().split(",")))
        );
        List<String> rentArea = building.getRentAreaEntities().stream()
                .map(r -> r.getValue().toString())
                .collect(Collectors.toList());
        buildingDTO.setRentArea(String.join(",", rentArea));
        return buildingDTO;
    }
}
