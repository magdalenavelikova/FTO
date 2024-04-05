package com.fto.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FamilyMemberDto {
    @NotEmpty
    private String familyName;
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

    public FamilyMemberDto() {
    }

    public String getFamilyName() {
        return familyName;
    }

    public FamilyMemberDto setFamilyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public String getName() {
        return name;
    }

    public FamilyMemberDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public FamilyMemberDto setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String getRole() {
        return role;
    }

    public FamilyMemberDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public FamilyMemberDto setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public FamilyMemberDto setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
