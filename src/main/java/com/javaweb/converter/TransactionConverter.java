package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO toTransactionDTO(TransactionEntity transactionEntity){
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        transactionDTO.setCustomerId(transactionEntity.getCustomer().getId());
        return transactionDTO;
    }

    public TransactionEntity toTransactionEntity(TransactionDTO transactionDTO, CustomerEntity customer){
        TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
        transactionEntity.setCustomer(customer);
        return  transactionEntity;
    }
}
