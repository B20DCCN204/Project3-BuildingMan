package com.javaweb.controller.admin;



import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.impl.BuildingService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private BuildingService buildingService;

    @GetMapping("admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("buildingSearch", buildingSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("listDistricts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
        }
        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
        DisplayTagUtils.of(request, buildingSearchResponse);
        //Lay data
        List<BuildingSearchResponse> buildingList = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchResponse.getPage() - 1, buildingSearchResponse.getMaxPageItems()));
        buildingSearchResponse.setListResult(buildingList);
        buildingSearchResponse.setTotalItems(buildingService.countTotalItems(buildingSearchRequest));

        mav.addObject("buildingList", buildingSearchResponse);
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
        BuildingDTO buildingDTO = buildingService.getBuildingById(buildingId);

        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("typeCodes", buildingType.type());
        mav.addObject("listDistricts", districtCode.type());
        return mav;
    }
}
