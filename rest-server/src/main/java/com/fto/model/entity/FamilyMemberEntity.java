package com.fto.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "family_members")
public class FamilyMemberEntity extends BaseEntity{

    @OneToOne
    private UserEntity user;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String pinCode;
    @OneToMany()
    private List<FamilyEntity> families;
    @OneToOne
    private FamilyRoleEntity role;
    @OneToOne
    private AgeCategoryEntity ageCategory;
        private String pictureUrl;

    public UserEntity getUser() {
        return user;
    }

    public FamilyMemberEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getName() {
        return name;
    }

    public FamilyMemberEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public FamilyMemberEntity setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public AgeCategoryEntity getAgeCategory() {
        return ageCategory;
    }

    public FamilyMemberEntity setAgeCategory(AgeCategoryEntity ageCategory) {
        this.ageCategory = ageCategory;
        return this;
    }

    public FamilyRoleEntity getRole() {
        return role;
    }

    public FamilyMemberEntity setRole(FamilyRoleEntity role) {
        this.role = role;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public FamilyMemberEntity setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public List<FamilyEntity> getFamilies() {
        return families;
    }

    public FamilyMemberEntity setFamilies(List<FamilyEntity> families) {
        this.families = families;
        return this;
    }
}
