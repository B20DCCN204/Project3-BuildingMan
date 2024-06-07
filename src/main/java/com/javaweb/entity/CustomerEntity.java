package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "company")
    private String company;
    @Column(name = "demand")
    private String demand;
    @Column(name = "status")
    private String status;
    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToMany
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TransactionEntity> transactionEntities = new ArrayList<>();

    public String getFullname() {
        return fullname;
    }

    public CustomerEntity setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CustomerEntity setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getDemand() {
        return demand;
    }

    public CustomerEntity setDemand(String demand) {
        this.demand = demand;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CustomerEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public CustomerEntity setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public CustomerEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }

    public List<TransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public CustomerEntity setTransactionEntities(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
        return this;
    }
}
