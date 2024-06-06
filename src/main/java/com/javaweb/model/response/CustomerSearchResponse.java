package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;

public class CustomerSearchResponse extends AbstractDTO {
    private String fullname;
    private String phone;
    private String email;
    private String company;
    private String demand;
    private String status;

    public String getFullname() {
        return fullname;
    }

    public CustomerSearchResponse setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerSearchResponse setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerSearchResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CustomerSearchResponse setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getDemand() {
        return demand;
    }

    public CustomerSearchResponse setDemand(String demand) {
        this.demand = demand;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CustomerSearchResponse setStatus(String status) {
        this.status = status;
        return this;
    }
}
