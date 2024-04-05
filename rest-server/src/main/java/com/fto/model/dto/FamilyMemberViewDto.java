package com.fto.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FamilyMemberViewDto {

    private String name;

    private String pinCode;

    private String role;
       private String ageCategory;
    private String pictureUrl;

    public FamilyMemberViewDto() {
    }

    public String getName() {
        return name;
    }

    public FamilyMemberViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public FamilyMemberViewDto setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String getRole() {
        return role;
    }

    public FamilyMemberViewDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public FamilyMemberViewDto setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public FamilyMemberViewDto setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
