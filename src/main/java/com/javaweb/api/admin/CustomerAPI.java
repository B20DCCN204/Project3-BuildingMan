package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("api/customer")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addOrUpdateCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customer = customerService.save(customerDTO);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteCustomers(@PathVariable("ids") List<Long> ids){
        customerService.deleteCustomers(ids);
        return ResponseEntity.status(HttpStatus.OK).body("Thực hiện xóa thành công");
    }

    @GetMapping("/{customerId}/staffs")
    public ResponseEntity<?> loadStaffs(@PathVariable("customerId") Long id){
        ResponseDTO response = customerService.getStaffs(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/assignment")
    public ResponseEntity<?> updateAssignmentCustomer(@Valid @RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        customerService.assignCustomerToStaffs(assignmentCustomerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Thực hiện giao khách hàng thành công");
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        customerService.save(transactionDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Tạo giao dịch thành công");
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable("id") Long id){
        TransactionDTO transactionDTO = customerService.getTransactionDetail(id);
        return ResponseEntity.ok(transactionDTO);
    }
}
