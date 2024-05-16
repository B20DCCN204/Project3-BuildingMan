package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @PostMapping
    public ResponseEntity<?> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        return ResponseEntity.ok(buildingDTO);
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){

    }
}
