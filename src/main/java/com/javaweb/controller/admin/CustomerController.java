package com.javaweb.controller.admin;

import com.javaweb.enums.transactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller(value="customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("admin/customer-list")
    public ModelAndView customerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("customerSearch", customerSearchRequest);
        DisplayTagUtils.of(request, customerSearchRequest);
        //Lay data
        List<CustomerSearchResponse> customerList = customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setListResult(customerList);
        customerSearchResponse.setTotalItems(customerService.countTotalItems(customerSearchRequest));

        mav.addObject("customerList", customerSearchResponse);

        mav.addObject("listStaffs", userService.getStaffs());
        return mav;
    }

    @GetMapping("admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit", customerDTO);
        return mav;
    }

    @GetMapping("admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        // Lấy customer
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setFullname("Kiều Minh Giang")
                        .setPhone("0245435435")
                                .setStatus("Đang xử lí");

        mav.addObject("transactionType", transactionType.type());
        mav.addObject("customerEdit", customerDTO);
        return mav;
    }
}
