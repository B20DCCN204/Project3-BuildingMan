package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "street")
    private String street;
    @Column(name = "ward")
    private String ward;
    @Column(name = "district")
    private String district;
    @Column(name = "structure")
    private String structure;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    @Column(name = "floorarea")
    private Integer floorArea;
    @Column(name = "direction")
    private String direction;
    @Column(name = "level")
    private String level;
    @Column(name = "rentprice")
    private Integer rentPrice;
    @Column(name = "rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name = "carfee")
    private String carFee;
    @Column(name = "motofee")
    private String motoFee;
    @Column(name = "overtimefee")
    private String overtimeFee;
    @Column(name = "waterfee")
    private String waterFee;
    @Column(name = "electricityfee")
    private String electricityFee;
    @Column(name = "deposit")
    private String deposit;
    @Column(name = "payment")
    private String payment;
    @Column(name = "renttime")
    private String rentTime;
    @Column(name = "decorationtime")
    private String decorationTime;
    @Column(name = "brokeragetee")
    private Double brokerageFee;
    @Column(name = "type")
    private String typeCode;
    @Column(name = "note")
    private String note;
    @Column(name = "linkofbuilding")
    private String linkOfBuilding;
    @Column(name = "map")
    private String map;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhone;

    @OneToMany(mappedBy = "building", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    public List<UserEntity> getUsers() {
        return users;
    }

    public BuildingEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }

    public List<RentAreaEntity> getRentAreaEntities() {
        return rentAreaEntities;
    }

    public BuildingEntity setRentAreaEntities(List<RentAreaEntity> rentAreaEntities) {
        this.rentAreaEntities = rentAreaEntities;
        return this;
    }


    public String getName() {
        return name;
    }

    public BuildingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public BuildingEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public BuildingEntity setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public BuildingEntity setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getStructure() {
        return structure;
    }

    public BuildingEntity setStructure(String structure) {
        this.structure = structure;
        return this;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public BuildingEntity setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
        return this;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public BuildingEntity setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public BuildingEntity setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public BuildingEntity setLevel(String level) {
        this.level = level;
        return this;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public BuildingEntity setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public BuildingEntity setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
        return this;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public BuildingEntity setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public String getCarFee() {
        return carFee;
    }

    public BuildingEntity setCarFee(String carFee) {
        this.carFee = carFee;
        return this;
    }

    public String getMotoFee() {
        return motoFee;
    }

    public BuildingEntity setMotoFee(String motoFee) {
        this.motoFee = motoFee;
        return this;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public BuildingEntity setOvertimeFee(String overtimeFee) {
        this.overtimeFee = overtimeFee;
        return this;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public BuildingEntity setWaterFee(String waterFee) {
        this.waterFee = waterFee;
        return this;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public BuildingEntity setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
        return this;
    }

    public String getDeposit() {
        return deposit;
    }

    public BuildingEntity setDeposit(String deposit) {
        this.deposit = deposit;
        return this;
    }

    public String getPayment() {
        return payment;
    }

    public BuildingEntity setPayment(String payment) {
        this.payment = payment;
        return this;
    }

    public String getRentTime() {
        return rentTime;
    }

    public BuildingEntity setRentTime(String rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public BuildingEntity setDecorationTime(String decorationTime) {
        this.decorationTime = decorationTime;
        return this;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public BuildingEntity setBrokerageFee(Double brokerageFee) {
        this.brokerageFee = brokerageFee;
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public BuildingEntity setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public String getNote() {
        return note;
    }

    public BuildingEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getLinkOfBuilding() {
        return linkOfBuilding;
    }

    public BuildingEntity setLinkOfBuilding(String linkOfBuilding) {
        this.linkOfBuilding = linkOfBuilding;
        return this;
    }

    public String getMap() {
        return map;
    }

    public BuildingEntity setMap(String map) {
        this.map = map;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public BuildingEntity setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getManagerName() {
        return managerName;
    }

    public BuildingEntity setManagerName(String managerName) {
        this.managerName = managerName;
        return this;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public BuildingEntity setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
        return this;
    }
}
