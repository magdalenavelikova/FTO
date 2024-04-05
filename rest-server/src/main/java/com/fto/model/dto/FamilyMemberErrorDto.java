package com.fto.model.dto;

public class FamilyMemberErrorDto {


    private final String name;
       private final String description;


    public FamilyMemberErrorDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
