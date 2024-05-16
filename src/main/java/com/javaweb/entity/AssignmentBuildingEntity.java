package com.javaweb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "buildingid", nullable = false)
    private BuildingEntity building;

    @ManyToOne
    @JoinColumn(name = "staffid", nullable = false)
    private UserEntity staff;

    public BuildingEntity getBuilding() {
        return building;
    }

    public AssignmentBuildingEntity setBuilding(BuildingEntity building) {
        this.building = building;
        return this;
    }

    public UserEntity getUser() {
        return staff;
    }

    public AssignmentBuildingEntity setUser(UserEntity staff) {
        this.staff = staff;
        return this;
    }
}
