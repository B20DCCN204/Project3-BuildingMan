package com.javaweb.controller.admin;

import com.javaweb.enums.statusTransaction;
import com.javaweb.enums.transactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
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
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
        }

        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setMaxPageItems(5);
        DisplayTagUtils.of(request, customerSearchResponse);
        List<CustomerSearchResponse> customerList = customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchResponse.getPage() - 1, customerSearchResponse.getMaxPageItems()));
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
        mav.addObject("statusType", statusTransaction.type());
        return mav;
    }

    @GetMapping("admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        // Lấy customer
        CustomerDTO customerDTO = customerService.getCustomerById(id);

        //CSKH
        List<TransactionDTO> listCSKH = customerService.getTransactionByCodeAndCustomer("CSKH", id);

        //DDX
        List<TransactionDTO> listDDX = customerService.getTransactionByCodeAndCustomer("DDX", id);

        mav.addObject("transactionType", transactionType.type());
        mav.addObject("customerEdit", customerDTO);
        mav.addObject("statusType", statusTransaction.type());
        mav.addObject("cskh", listCSKH);
        mav.addObject("ddx", listDDX);
        return mav;
    }
}
