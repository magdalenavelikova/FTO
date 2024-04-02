package com.fto.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "families")
public class FamilyEntity extends BaseEntity {
    @OneToOne
    private UserEntity user;
    @Column(nullable = false)
    private String name;
    @OneToMany
    private List<FamilyMemberEntity> members = new ArrayList<>();

    public FamilyEntity() {

    }

    public String getName() {
        return name;
    }

    public FamilyEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<FamilyMemberEntity> getMembers() {
        return members;
    }

    public FamilyEntity setMembers(List<FamilyMemberEntity> members) {
        this.members = members;
        return this;
    }
}
