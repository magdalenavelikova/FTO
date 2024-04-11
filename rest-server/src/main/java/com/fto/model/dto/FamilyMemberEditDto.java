package com.fto.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FamilyMemberEditDto {
    @NotEmpty
    private String familyId;
    @NotEmpty
    private Long id;
    @NotEmpty
    @Size(min = 3, max = 20, message = "Please enter between 3 and 20 characters.")
    private String name;
    @NotEmpty
    @Size(min = 4, max = 4, message = "Please enter 4 characters.")
    private String pinCode;
    @NotEmpty
    private String role;
    @NotEmpty
    private String ageCategory;
    private String pictureUrl;

    public FamilyMemberEditDto() {
    }

    public String getFamilyId() {
        return familyId;
    }

    public FamilyMemberEditDto setFamilyId(String familyId) {
        this.familyId = familyId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public FamilyMemberEditDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FamilyMemberEditDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public FamilyMemberEditDto setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String getRole() {
        return role;
    }

    public FamilyMemberEditDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public FamilyMemberEditDto setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public FamilyMemberEditDto setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
