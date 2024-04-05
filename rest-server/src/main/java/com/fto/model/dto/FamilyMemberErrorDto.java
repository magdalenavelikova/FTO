package com.fto.model.dto;

public class FamilyErrorDto {


    private final String familyName;
       private final String description;

    public FamilyErrorDto(String familyName, String description) {
        this.familyName = familyName;
        this.description = description;

    }



    public String getFamilyName() {
        return familyName;
    }

    public String getDescription() {
        return description;
    }

}
