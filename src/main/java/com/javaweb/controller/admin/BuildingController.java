package com.javaweb.controller.admin;



import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private UserService userService;

    @GetMapping("admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("buildingSearch", buildingSearchRequest);
        //Lay data
        List<BuildingSearchResponse> buildingList = new ArrayList<>();
        BuildingSearchResponse building1 = new BuildingSearchResponse();
        building1.setId(1L);
        building1.setName("Vinhome West Point");
        building1.setAddress("Hà Nội");
        building1.setNumberOfBasement(1L);
        building1.setFloorArea(300L);
        building1.setManagerName("Kiều Minh Giang");
        building1.setManagerPhone("012334325");
        building1.setRentArea("100, 200, 300");
        building1.setRentPrice(1000L);
        building1.setBrokerageFee(245.35D);
        buildingList.add(building1);
        mav.addObject("buildingList", buildingList);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("listDistricts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }

    @GetMapping("admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("typeCodes", buildingType.type());
        mav.addObject("listDistricts", districtCode.type());
        return mav;
    }

    @GetMapping("admin/building-edit-{buildingId}")
    public ModelAndView buildingEdit(@PathVariable("buildingId") Long buildingId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        // Get building data by id from db
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(buildingId);
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("typeCodes", buildingType.type());
        mav.addObject("listDistricts", districtCode.type());
        return mav;
    }
}
