package com.fto.model.entity;

import com.fto.model.enums.AgeCategoryEnum;
import com.fto.model.enums.FamilyRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "family_members")
public class FamilyMemberEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String pinCode;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FamilyRoleEnum role;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeCategoryEnum ageCategory;
    private String pictureUrl;
    @ManyToOne
    private FamilyEntity family;

    public FamilyMemberEntity() {
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

    public FamilyRoleEnum getRole() {
        return role;
    }

    public FamilyMemberEntity setRole(FamilyRoleEnum role) {
        this.role = role;
        return this;
    }

    public AgeCategoryEnum getAgeCategory() {
        return ageCategory;
    }

    public FamilyMemberEntity setAgeCategory(AgeCategoryEnum ageCategory) {
        this.ageCategory = ageCategory;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public FamilyMemberEntity setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public FamilyEntity getFamily() {
        return family;
    }

    public FamilyMemberEntity setFamily(FamilyEntity family) {
        this.family = family;
        return this;
    }
}
