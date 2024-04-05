package com.fto.model.dto;

import java.util.ArrayList;
import java.util.List;

public class FamilyViewDto {

   private Long id;
    private String name;

    private List<FamilyMemberViewDto> members =new ArrayList<>();

    public FamilyViewDto() {
    }


    public Long getId() {
        return id;
    }

    public FamilyViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FamilyViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<FamilyMemberViewDto> getMembers() {
        return members;
    }

    public FamilyViewDto setMembers(List<FamilyMemberViewDto> members) {
        this.members = members;
        return this;
    }
}
