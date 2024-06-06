package com.javaweb.service;

import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    int countTotalItems(CustomerSearchRequest customerSearchRequest);
}
