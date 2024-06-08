package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.statusTransaction;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerConverter converter;
    @Autowired
    private TransactionConverter transactionConverter;
    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        List<CustomerEntity> customers = customerRepository.findAll(customerSearchRequest, pageable);
        List<CustomerSearchResponse> results = new ArrayList<>();
        for(CustomerEntity customer : customers){
            results.add(converter.toCustomerSearchResponse(customer));
        }
        return results;
    }

    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countTotalItem(customerSearchRequest);
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customer = converter.toCustomerEntity(customerDTO);
        return converter.toCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return converter.toCustomerDTO(customerRepository.getOne(id));
    }

    @Override
    @Transactional
    public void deleteCustomers(List<Long> ids) {
        List<CustomerEntity> customerEntities = customerRepository.findAllById(ids)
                .stream()
                .map(customer -> customer.setActive(false).setStatus(statusTransaction.DA_XU_LY.name()))
                .collect(Collectors.toList());
    }


    @Override
    public ResponseDTO getStaffs(Long customerId) {
        CustomerEntity customer = customerRepository.getOne(customerId);
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = customer.getUsers();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO result = new ResponseDTO();
        for(UserEntity u : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(u.getId());
            staffResponseDTO.setFullName(u.getFullName());
            if(staffAssignment.contains(u)){
                staffResponseDTO.setChecked("checked");
            }else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        result.setData(staffResponseDTOS);
        result.setMessage("success");
        return result;
    }

    @Override
    public void assignCustomerToStaffs(AssignmentCustomerDTO assignmentCustomerDTO) {
        Long customerId = assignmentCustomerDTO.getCustomerId();
        List<Long> staffIds = assignmentCustomerDTO.getStaffs();

        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer not found!")
        );

        List<UserEntity> staffsToAssign = userRepository.findAllById(staffIds);

        customer.setUsers(staffsToAssign);

        customerRepository.save(customer);
    }

    @Override
    public List<TransactionDTO> getTransactionByCodeAndCustomer(String code, Long customerId) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByCodeAndCustomerId(code, customerId);
        List<TransactionDTO> result = transactionEntities.stream()
                .map(transactionEntity -> transactionConverter.toTransactionDTO(transactionEntity))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public void save(TransactionDTO transactionDTO) {
        Long customerId = transactionDTO.getCustomerId();
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer not found!")
        );
        TransactionEntity transactionEntity = transactionConverter.toTransactionEntity(transactionDTO, customer);
        Long transactionId = transactionDTO.getId();
        if(transactionId != null){
            TransactionEntity old = transactionRepository.getOne(transactionId);
            transactionEntity.setCreatedDate(old.getCreatedDate());
            transactionEntity.setCreatedBy(old.getCreatedBy());
        }
        transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionDTO getTransactionDetail(Long id) {
        return transactionConverter.toTransactionDTO(transactionRepository.getOne(id));
    }


}
