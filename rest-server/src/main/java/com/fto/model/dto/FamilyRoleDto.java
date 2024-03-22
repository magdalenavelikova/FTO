package com.fto.model.dto;
public class FamilyRoleDto {
    private Long id;

    private String familyRole;

    public FamilyRoleDto() {
    }

    public Long getId() {
        return id;
    }

    public FamilyRoleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFamilyRole() {
        return familyRole;
    }

    public FamilyRoleDto setFamilyRole(String familyRole) {
        this.familyRole = familyRole;
        return this;
    }
}
