package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.statusTransaction;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerSearchResponse toCustomerSearchResponse(CustomerEntity customer){
        CustomerSearchResponse customerSearchResponse = modelMapper.map(customer, CustomerSearchResponse.class);
        if(customer.getStatus() != null){
            customerSearchResponse.setStatus(statusTransaction.valueOf(customer.getStatus()).getStatus());
        }
        return customerSearchResponse;
    }

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO){
        CustomerEntity customer = modelMapper.map(customerDTO, CustomerEntity.class);
        return customer;
    }

    public CustomerDTO toCustomerDTO(CustomerEntity customer){
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }
}
