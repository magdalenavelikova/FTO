package com.fto.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class FamilyDto {

    @NotEmpty
    @Size(min = 3, max = 20, message = "Please enter between 3 and 20 characters.")
    private String name;

    private List<FamilyMemberDto> members =new ArrayList<>();

    public FamilyDto() {
    }



    public String getName() {
        return name;
    }

    public FamilyDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<FamilyMemberDto> getMembers() {
        return members;
    }

    public FamilyDto setMembers(List<FamilyMemberDto> members) {
        this.members = members;
        return this;
    }
}
