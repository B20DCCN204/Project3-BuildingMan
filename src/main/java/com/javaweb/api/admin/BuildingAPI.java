package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @PostMapping
    public BuildingDTO addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        BuildingDTO response = buildingService.save(buildingDTO);
        return response;
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteBuilding(@PathVariable("ids") List<Long> ids){
        buildingService.deleteBuildings(ids);
        return ResponseEntity.status(HttpStatus.OK).body("Thực hiện xóa thành công");
    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO loadStaffs(@PathVariable("buildingId") Long buildingId){
        ResponseDTO result = buildingService.getStaffs(buildingId);
        return result;
    }

    @PostMapping("/assignment")
    public ResponseEntity<?> updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.assignBuildingToStaffs(assignmentBuildingDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Thực hiện giao tòa nhà thành công");
    }
}
