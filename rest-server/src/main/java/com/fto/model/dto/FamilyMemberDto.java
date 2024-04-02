package com.fto.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FamilyMemberDto {
    @NotEmpty
    @Size(min = 3, max = 20, message = "Please enter between 3 and 20 characters.")
    private String name;
    @NotEmpty
    @Size(min = 4, max = 4, message = "Please enter 4 characters.")
    private String pinCode;

    @NotEmpty
    private FamilyRoleDto familyRole;
    @NotEmpty
    private AgeCategoryDto ageCategory;
    private String pictureUrl;

    public FamilyMemberDto() {
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

    public FamilyRoleDto getFamilyRole() {
        return familyRole;
    }

    public FamilyMemberDto setFamilyRole(FamilyRoleDto familyRole) {
        this.familyRole = familyRole;
        return this;
    }

    public AgeCategoryDto getAgeCategory() {
        return ageCategory;
    }

    public FamilyMemberDto setAgeCategory(AgeCategoryDto ageCategory) {
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
