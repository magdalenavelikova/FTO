package com.fto.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "families")
public class FamilyEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;
}
