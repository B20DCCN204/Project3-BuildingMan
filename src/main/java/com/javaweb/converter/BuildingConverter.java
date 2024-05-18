package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity building){
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(building, BuildingSearchResponse.class);

        buildingSearchResponse.setAddress(building.getStreet()+", "+building.getWard()+", " +
                (building.getDistrict() != null ? districtCode.valueOf(building.getDistrict()).getDistrictName() : ""));

        List<RentAreaEntity> rentAreaEntityList = building.getRentAreaEntities();
        String rentAreas = rentAreaEntityList.stream().map(ra -> String.valueOf(ra.getValue())).collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentAreas);

        return buildingSearchResponse;
    }
}
