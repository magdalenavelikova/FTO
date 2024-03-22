package com.fto.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "families")
public class FamilyEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;

    public FamilyEntity() {
    }

    public String getName() {
        return name;
    }

    public FamilyEntity setName(String name) {
        this.name = name;
        return this;
    }
}
