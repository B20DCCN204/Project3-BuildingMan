package com.javaweb.converter;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentAreaConverter {
    public List<RentAreaEntity> toListRentAreaEntity(String rentAreaString, BuildingEntity building){
        return Arrays.stream(rentAreaString.split(","))
                .map(String::trim)
                .map(value -> {
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();
                    rentAreaEntity.setValue(Integer.parseInt(value));
                    rentAreaEntity.setBuilding(building);
                    return rentAreaEntity;
                })
                .collect(Collectors.toList());
    }
}
