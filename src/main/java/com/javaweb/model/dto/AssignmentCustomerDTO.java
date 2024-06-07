package com.javaweb.model.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AssignmentCustomerDTO {
    private Long customerId;

    @NotNull(message = "You must select at least one staff")
    private List<Long> staffs;

    public Long getCustomerId() {
        return customerId;
    }

    public AssignmentCustomerDTO setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public List<Long> getStaffs() {
        return staffs;
    }

    public AssignmentCustomerDTO setStaffs(List<Long> staffs) {
        this.staffs = staffs;
        return this;
    }
}
