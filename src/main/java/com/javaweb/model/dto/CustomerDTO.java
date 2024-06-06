package com.javaweb.model.dto;

import javax.validation.constraints.NotBlank;

public class CustomerDTO extends AbstractDTO{
    @NotBlank(message = "Name is mandatory")
    private String fullname;
    private String managementStaff;
    @NotBlank(message = "Name is mandatory")
    private String phone;
    private String email;
    private String demand;
    private String status;
    private String company;

    public String getFullname() {
        return fullname;
    }

    public CustomerDTO setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getManagementStaff() {
        return managementStaff;
    }

    public CustomerDTO setManagementStaff(String managementStaff) {
        this.managementStaff = managementStaff;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDemand() {
        return demand;
    }

    public CustomerDTO setDemand(String demand) {
        this.demand = demand;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CustomerDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CustomerDTO setCompany(String company) {
        this.company = company;
        return this;
    }
}
