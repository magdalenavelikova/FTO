package com.fto.model.dto;

import com.fto.validation.annotation.UniqueFamilyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class FamilyEditDto {

    private Long id;
    @NotEmpty
    @Size(min = 3, max = 20, message = "Please enter between 3 and 20 characters.")
    private String name;

    private List<FamilyMemberEditDto> members = new ArrayList<>();

    public FamilyEditDto() {
    }


    public Long getId() {
        return id;
    }

    public FamilyEditDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FamilyEditDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<FamilyMemberEditDto> getMembers() {
        return members;
    }

    public FamilyEditDto setMembers(List<FamilyMemberEditDto> members) {
        this.members = members;
        return this;
    }
}
