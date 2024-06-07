package com.javaweb.service;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    int countTotalItems(CustomerSearchRequest customerSearchRequest);

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    void deleteCustomers(List<Long> ids);

    ResponseDTO getStaffs(Long customerId);

    void assignCustomerToStaffs(AssignmentCustomerDTO assignmentCustomerDTO);

    List<TransactionDTO> getTransactionByCodeAndCustomer(String code, Long customerId);

    void save(TransactionDTO transactionDTO);

    TransactionDTO getTransactionDetail(Long id);
}
