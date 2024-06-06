package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;

public class CustomerSearchRequest extends AbstractDTO{
    private String fullname;
    private String phone;
    private String email;
    private Long staffId;

    public String getFullname() {
        return fullname;
    }

    public CustomerSearchRequest setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerSearchRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerSearchRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getStaffId() {
        return staffId;
    }

    public CustomerSearchRequest setStaffId(Long staffId) {
        this.staffId = staffId;
        return this;
    }
}
